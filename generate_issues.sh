#!/bin/bash
export GH_TOKEN="${GH_TOKEN:-$GITHUB_TOKEN}"

# =====================================================
# Libris Issue Auto Generator
# =====================================================

REPO="tatsuhikoabgm-dev/Libris"      # ★社長のリポ名
PROJECT_ID="PVT_kwHODfrBac4BJL5g"       # ★あとで取得して置き換える
TEMPLATE_DIR="./templates"
SOURCE="./ISSUES.md"

# テンプレ割り当て
get_template() {
  local title="$1"

  if [[ "$TITLE" == *"Entity"* ]]; then
  TITLE="[Entity] $TITLE"
elif [[ "$TITLE" == *"Dto"* ]] || [[ "$TITLE" == *"Dto を作成する"* ]]; then
  TITLE="[DTO] $TITLE"
elif [[ "$TITLE" == *"Enum"* ]]; then
  TITLE="[Enum] $TITLE"
elif [[ "$TITLE" == *"Config"* ]]; then
  TITLE="[Config] $TITLE"
elif [[ "$TITLE" == *"Controller"* ]]; then
  TITLE="[Controller] $TITLE"
elif [[ "$TITLE" == *"Service"* ]]; then
  TITLE="[Service] $TITLE"
elif [[ "$TITLE" == *"Mapper"* ]]; then
  TITLE="[Mapper] $TITLE"
else
  echo "⚠ No template found for: $TITLE"
  continue
fi

}

# Issue 作成処理
create_issue() {
  local title="$1"
  local template_file="$2"

  echo "👉 Creating issue: $title (template: $template_file)"

  BODY_FILE=$(mktemp)

  cp "$TEMPLATE_DIR/$template_file" "$BODY_FILE"

  # 置換用の名前（[Entity] UsersEntity を作成する → UsersEntity）
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
# メインループ
# =====================================================
echo "===== Libris Issue Auto Generator ====="
echo ""

while read -r line; do

  if [[ "$line" =~ "- \[ \]" ]]; then

    # タイトル抽出
    RAW_TITLE=$(echo "$line" | sed -E 's/- \[ \] //')

    TITLE="$RAW_TITLE"

    # 自動で前にカテゴリタグ付ける
    if [[ "$TITLE" == *"Entity を作成する"* ]]; then
      TITLE="[Entity] $TITLE"
    elif [[ "$TITLE" == *"Dto を作成する"* ]]; then
      TITLE="[DTO] $TITLE"
    elif [[ "$TITLE" == *"Enum"* ]]; then
      TITLE="[Enum] $TITLE"
    elif [[ "$TITLE" == *"を実装する"* ]]; then
      # Mapper/Service/Controller は事前分類済み
      TITLE="$TITLE"
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
