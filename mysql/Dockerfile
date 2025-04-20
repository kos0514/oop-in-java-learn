FROM mysql:8.0

# 日本語環境の設定
ENV LANG ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8

# タイムゾーンの設定
ENV TZ Asia/Tokyo

# 初期化スクリプトをコピー（必要に応じて）
COPY ./init.sql /docker-entrypoint-initdb.d/