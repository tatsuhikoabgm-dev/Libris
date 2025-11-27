#!/bin/bash
export GH_TOKEN="${GH_TOKEN:-$GITHUB_TOKEN}"

# =====================================================
# Libris Issue Auto Generator（決定版）
# =====================================================

REPO="tatsuhikoabgm-dev/Libris"        # ★社長のリポ名
PROJECT_ID="PVT_kwHODfrBac4BJL5g"      # ★GitHub CLI で取得した Project ID
TEMPLATE_DIR="./templates"
SOURCE="./ISSUES.md"

# =====================================================
# タイトル → テンプレート判定
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
# タイトル → 自動タグ追加
# =====================================================
add_tag() {
  local title="$1"

  # Entity
  if [[ "$title" == *"Entity を作成する"* ]]; then
    echo "[Entity] $title"
    return
  fi

  # DTO
  if [[ "$title" == *"Dto を作成する"* ]]; then
    echo "[DTO] $title"
    return
  fi

  # Enum
  if [[ "$title" == *"Enum"* ]]; then
    echo "[Enum] $title"
    return
  fi

  # Mapper（find/insert/update 系）
  if [[ "$title" == *"find"* ]] || [[ "$title" == *"insert"* ]] || [[ "$title" == *"update"* ]]; then
    echo "[Mapper] $title"
    return
  fi

  # Controller
  if [[ "$title" == *"Controller"* ]]; then
    echo "[Controller] $title"
    return
  fi

  # Config
  if [[ "$title" == *"Config"* ]]; then
    echo "[Config] $title"
    return
  fi

  # Service（残りの「～を実装する」系）
  if [[ "$title" == *"を実装する"* ]]; then
    echo "[Service] $title"
    return
  fi

  # マッチなし
  echo "$title"
}

# =====================================================
# Issue 作成処理
# =====================================================
create_issue() {
  local title="$1"
  local template_file="$2"

  echo "👉 Creating issue: $title (template: $template_file)"

  BODY_FILE=$(mktemp)

  cp "$TEMPLATE_DIR/$template_file" "$BODY_FILE"

  # 名前抽出（[Tag] xxx を作成する → xxx）
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
# メイン処理
# =====================================================
echo "===== Libris Issue Auto Generator ====="
echo ""

while read -r line; do
  if [[ "$line" =~ "- \[ \]" ]]; then

    RAW_TITLE=$(echo "$line" | sed -E 's/- \[ \] //')

    TITLE=$(add_tag "$RAW_TITLE")

    TEMPLATE=$(get_template "$TITLE")

    if [[ -z "$TEMPLATE" ]]; then
      echo "⚠ No template found for: $TITLE"
      continue
    fi

    create_issue "$TITLE" "$TEMPLATE"

  fi
done < "$SOURCE"

echo ""
echo "🎉 ALL Issues Generated Successfully!"
