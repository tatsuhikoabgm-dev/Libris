#!/bin/bash
export GH_TOKEN="${GH_TOKEN:-$GITHUB_TOKEN}"

echo "===== Libris Issue Auto Generator (DEBUG MODE) ====="
echo ""

REPO="tatsuhikoabgm-dev/Libris"
PROJECT_ID="PVT_kwHODfrBac4BJL5g"
TEMPLATE_DIR="./templates"
SOURCE="./ISSUES.md"

# =====================================================
# template detection
# =====================================================
get_template() {
  local title="$1"

  if [[ "$title" == *"[Entity]"* ]]; then
    echo "entity.md"
  elif [[ "$title" == *"[DTO]"* ]]; then
    echo "dto.md"
  elif [[ "$title" == *"[Enum]"* ]]; then
    echo "enum.md"
  elif [[ "$title" == *"[Mapper]"* ]]; then
    echo "mapper.md"
  elif [[ "$title" == *"[Service]"* ]]; then
    echo "service.md"
  elif [[ "$title" == *"[Controller]"* ]]; then
    echo "controller.md"
  elif [[ "$title" == *"[Config]"* ]]; then
    echo "config.md"
  else
    echo ""
  fi
}

# =====================================================
# auto prepend tag
# =====================================================
add_tag() {
  local title="$1"

  if [[ "$title" == *"Entity を作成する"* ]]; then
    echo "[Entity] $title"
    return
  fi

  if [[ "$title" == *"Dto を作成する"* ]]; then
    echo "[DTO] $title"
    return
  fi

  if [[ "$title" == *"Enum"* ]]; then
    echo "[Enum] $title"
    return
  fi

  if [[ "$title" == *"find"* ]] || [[ "$title" == *"insert"* ]] || [[ "$title" == *"update"* ]]; then
    echo "[Mapper] $title"
    return
  fi

  if [[ "$title" == *"Controller"* ]]; then
    echo "[Controller] $title"
    return
  fi

  if [[ "$title" == *"Config"* ]]; then
    echo "[Config] $title"
    return
  fi

  if [[ "$title" == *"を実装する"* ]]; then
    echo "[Service] $title"
    return
  fi

  echo "$title"
}

# =====================================================
# issue creation logic
# =====================================================
create_issue() {
  local title="$1"
  local template_file="$2"

  echo "👉 Creating issue: $title (template: $template_file)"

  BODY_FILE=$(mktemp)
  cp "$TEMPLATE_DIR/$template_file" "$BODY_FILE"

  local NAME=$(echo "$title" | sed -E 's/^\[[^]]+\] //; s/ を.*//')

  sed -i "s/{EntityName}/$NAME/g" "$BODY_FILE"
  sed -i "s/{DtoName}/$NAME/g" "$BODY_FILE"
  sed -i "s/{EnumName}/$NAME/g" "$BODY_FILE"
  sed -i "s/{methodName}/$NAME/g" "$BODY_FILE"
  sed -i "s/{ConfigName}/$NAME/g" "$BODY_FILE"

  ISSUE_NUMBER=$(gh issue create \
    --repo "$REPO" \
    --title "$title" \
    --body-file "$BODY_FILE" \
    --label "auto" \
    --json number \
    --jq ".number")

  echo "✔ Created issue #$ISSUE_NUMBER"

  echo "📌 Adding to project..."
  gh project item-add "$PROJECT_ID" --content-id "$ISSUE_NUMBER" >/dev/null
  echo "   → Added to Project"
}

# =====================================================
# Main loop with debug
# =====================================================

echo "Reading ISSUES.md ..."
echo "-----------------------------------------"

while IFS= read -r line; do
  # Debug display raw line
  echo "LINE(raw): $line"

  # Debug: Does it match check-box?
  if [[ "$line" =~ -\ \[\ \] ]]; then
    echo "  → MATCH: this line is a task"
  else
    echo "  → NO MATCH"
    continue
  fi

  RAW_TITLE=$(echo "$line" | sed -E 's/- \[ \] //')
  echo "  Extracted title: $RAW_TITLE"

  TITLE=$(add_tag "$RAW_TITLE")
  echo "  After add_tag:   $TITLE"

  TEMPLATE=$(get_template "$TITLE")
  echo "  Template:        $TEMPLATE"

  if [[ -z "$TEMPLATE" ]]; then
    echo "  ⚠ SKIP (No template found)"
    continue
  fi

  create_issue "$TITLE" "$TEMPLATE"

  echo "-----------------------------------------"

done < "$SOURCE"

echo ""
echo "🎉 ALL Issues Generated Successfully!"
