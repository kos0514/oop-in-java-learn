#!/bin/bash
set -e

# ホストからマウントされた init.sql を確認
if [ -f /db-init/init.sql ]; then
    echo "Copying init.sql to MySQL init directory..."
    cp /db-init/init.sql /docker-entrypoint-initdb.d/
else
    echo "No init.sql found in /db-init/init.sql, skipping."
fi

# テスト用スキーマ作成SQLを確認
if [ -f /db-init/create_test_schema.sql ]; then
    echo "Copying create_test_schema.sql to MySQL init directory..."
    cp /db-init/create_test_schema.sql /docker-entrypoint-initdb.d/02-create_test_schema.sql
else
    echo "No create_test_schema.sql found, skipping."
fi

# 元のエントリポイントスクリプトを実行
exec /usr/local/bin/docker-entrypoint.sh "$@"
