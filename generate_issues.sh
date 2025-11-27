#!/bin/bash
export GH_TOKEN="${GH_TOKEN:-$GITHUB_TOKEN}"

# =====================================================
# Libris Issue Auto Generator
# =====================================================

REPO="tatsuhikoabgm-dev/Libris"     # ★社長のリポ名
PROJECT_ID="PVT_kwHODfrBac4BJL5g"   # ★取得済みのプロジェクトID
TEMPLATE_DIR="./templates"
SOURCE="./ISSUES.md"

# =====================================================
# テンプレ判定（template ファイル名だけ返す）
# =====================================================
get_template() {
  local title="$1"

  if [[ "$title" == *"Entity"* ]]; then
    echo "entity.md"
  elif [[ "$title" == *"Dto"* ]]; then
    echo "dto.md"
  elif [[ "$title" == *"Enum"* ]]; then
    echo "enum.md"
  elif [[ "$title" == *"Config"* ]]; then
    echo "config.md"
  elif [[ "$title" == *"Controller"* ]]; then
    echo "controller.md"
  elif [[ "$title" == *"Service"* ]]; then
    echo "service.md"
  elif [[ "$title" == *"Mapper"* ]]; then
    echo "mapper.md"
  else
    echo ""
  fi
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

  # 置換用の名前（例：[Entity] UsersEntity を作成 → UsersEntity）
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
# メインループ（ISSUES.md を読み取る）
# =====================================================
echo "===== Libris Issue Auto Generator ====="
echo ""

while read -r line; do

  if [[ "$line" =~ "- \[ \]" ]]; then

    RAW_TITLE=$(echo "$line" | sed -E 's/- \[ \] //')
    TITLE="$RAW_TITLE"

    # ===== カテゴリ判別（TITLE は上書きせず付与だけ） =====
    CATEGORY=""
    if [[ "$TITLE" == *"Entity"* ]]; then
      CATEGORY="[Entity]"
    elif [[ "$TITLE" == *"Dto"* ]]; then
      CATEGORY="[DTO]"
    elif [[ "$TITLE" == *"Enum"* ]]; then
      CATEGORY="[Enum]"
    elif [[ "$TITLE" == *"Mapper"* ]]; then
      CATEGORY="[Mapper]"
    elif [[ "$TITLE" == *"Service"* ]]; then
      CATEGORY="[Service]"
    elif [[ "$TITLE" == *"Controller"* ]]; then
      CATEGORY="[Controller]"
    elif [[ "$TITLE" == *"Config"* ]]; then
      CATEGORY="[Config]"
    fi

    # タイトルにカテゴリタグを前置
    if [[ -n "$CATEGORY" ]]; then
      TITLE="$CATEGORY $TITLE"
    fi

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
