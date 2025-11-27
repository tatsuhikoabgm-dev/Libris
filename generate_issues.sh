#!/bin/bash
export GH_TOKEN="${GH_TOKEN:-$GITHUB_TOKEN}"

# =====================================================
# Libris Issue Auto Generator（本番モード）
# =====================================================

REPO="tatsuhikoabgm-dev/Libris"
PROJECT_ID="PVT_kwHODfrBac4BJL5g"
TEMPLATE_DIR="./templates"
SOURCE="./ISSUES.md"

# -----------------------------
# タイトル → テンプレート判定
# -----------------------------
get_template() {
  local title="$1"

  if [[ "$title" == *"[Entity]"* ]]; then echo "entity.md"; return; fi
  if [[ "$title" == *"[DTO]"* ]]; then echo "dto.md"; return; fi
  if [[ "$title" == *"[Enum]"* ]]; then echo "enum.md"; return; fi
  if [[ "$title" == *"[Mapper]"* ]]; then echo "mapper.md"; return; fi
  if [[ "$title" == *"[Service]"* ]]; then echo "service.md"; return; fi
  if [[ "$title" == *"[Controller]"* ]]; then echo "controller.md"; return; fi
  if [[ "$title" == *"[Config]"* ]]; then echo "config.md"; return; fi

  echo ""
}

# -----------------------------
# タイトル → 種類タグ付け
# -----------------------------
add_tag() {
  local title="$1"

  if [[ "$title" == *"Entity を作成する"* ]]; then echo "[Entity] $title"; return; fi
  if [[ "$title" == *"Dto"* ]]; then echo "[DTO] $title"; return; fi
  if [[ "$title" == *"Enum"* ]]; then echo "[Enum] $title"; return; fi
  if [[ "$title" == *"find"* || "$title" == *"insert"* || "$title" == *"update"* ]]; then echo "[Mapper] $title"; return; fi
  if [[ "$title" == *"Controller"* ]]; then echo "[Controller] $title"; return; fi
  if [[ "$title" == *"Config"* ]]; then echo "[Config] $title"; return; fi
  if [[ "$title" == *"を実装する"* ]]; then echo "[Service] $title"; return; fi

  echo "$title"
}

# -----------------------------
# Issue 作成
# -----------------------------
create_issue() {
  local title="$1"
  local template_file="$2"

  echo "👉 Creating issue: $title"

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
  echo "   → Added"
}

# -----------------------------
# メイン処理
# -----------------------------
echo "===== Libris Issue Auto Generator ====="

while read -r line; do
  if [[ "$line" =~ "- [ ]" ]]; then
    RAW_TITLE=$(echo "$line" | sed -E 's/- \[ \] //')
    TITLE=$(add_tag "$RAW_TITLE")
    TEMPLATE=$(get_template "$TITLE")

    if [[ -z "$TEMPLATE" ]]; then
      echo "⚠ Skipped (no template): $TITLE"
      continue
    fi

    create_issue "$TITLE" "$TEMPLATE"
  fi
done < "$SOURCE"

echo "🎉 ALL Issues Created!"
