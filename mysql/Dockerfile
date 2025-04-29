FROM mysql:8.0

# 日本語環境の設定
ENV LANG ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8

# タイムゾーンの設定
ENV TZ Asia/Tokyo

# entrypoint.sh をコンテナにコピー
COPY ./entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/entrypoint.sh

# エントリポイントを設定
ENTRYPOINT ["entrypoint.sh"]

# デフォルトコマンドを設定
CMD ["mysqld"]
