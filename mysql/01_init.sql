-- データベースの選択
USE transmigration;

-- 共通テーブル定義を読み込む
SOURCE /db-init/00_common_tables.sql;

-- 基本種族（スタンダード）
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('human', 'ヒューマン', 'Human', '「適応力」- 経験値獲得量+10%', 'どの職業にも適応できる柔軟性が魅力なの',
        'STANDARD'),
       ('elf', 'エルフ', 'Elf', '「自然の共鳴」- 魔法消費MP-15%', '長寿で自然と調和した生き方をする魔法種族なの',
        'STANDARD'),
       ('dwarf', 'ドワーフ', 'Dwarf', '「鋼の意志」- 毒・スタン耐性+30%', '山や地下で暮らす頑健な鍛冶職人種族なの',
        'STANDARD'),
       ('orc', 'オーク', 'Orc', '「血の渇き」- HP30%以下で攻撃力+25%', '圧倒的な身体能力を持つ好戦的な種族なの',
        'STANDARD'),
       ('halfling', 'ハーフリング', 'Halfling', '「幸運の波」- クリティカル率+10%',
        '小柄で俊敏、意外な場面で力を発揮する種族なの', 'STANDARD'),
       ('dragonkin', 'ドラゴンキン', 'Dragonkin', '「竜の吐息」- 属性攻撃が使用可能', '竜の血を引く誇り高い戦士種族なの',
        'STANDARD');

-- トリッキー種族（ユニーク）
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('slime', 'スライム', 'Slime', '「分裂」- HP30%以下で一度だけ分身を作成',
        '柔軟な体で様々な形に変化できる粘体種族なの', 'UNIQUE'),
       ('spirit', 'スピリット', 'Spirit', '「霊体化」- 物理攻撃30%回避', '実体がなく精神エネルギーで存在する霊的種族なの',
        'UNIQUE'),
       ('android', 'アンドロイド', 'Android', '「システムアップグレード」- 10レベルごとに1つの能力上限突破',
        '魔導先進国で開発された自我を持つ機械生命体なの', 'UNIQUE'),
       ('chaos_entity', 'カオスエンティティ', 'Chaos Entity', '「混沌の力」- 戦闘中にランダム効果発生',
        '次元の狭間から現れた法則に縛られない不安定な存在なの', 'UNIQUE'),
       ('fae', 'フェイ', 'Fae', '「妖精の粉」- 1日1回幻影作成可能', '妖精の国から来た、いたずら好きで気まぐれな種族なの',
        'UNIQUE'),
       ('vampire', 'ヴァンパイア', 'Vampire', '「血の渇望」- 攻撃時HP吸収確率+15%',
        '不死の力を持つ夜行性の高貴な種族なの', 'UNIQUE');

-- クトゥルフ神話系種族
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('deep_one', '深きもの', 'Deep One', '「海の祝福」- 水中で能力+20%、陸上-10%',
        '海底に住む魚人のような種族で、年齢を重ねるほど強くなるの', 'UNIQUE'),
       ('shoggoth_hybrid', 'ショゴス変異体', 'Shoggoth Hybrid', '「形態変化」- 戦闘中1回だけ能力値再分配可能',
        '常に形を変え続ける原始的な混沌生命体の血を引く存在なの', 'UNIQUE'),
       ('elder_blood', '古の血脈', 'Elder Blood', '「禁断の知識」- 特殊魔法解読可能、使用時精神ダメージ',
        '宇宙の古の存在との接触により変異した血統を持つ人間なの', 'UNIQUE'),
       ('crawling_chaos', '這い寄る混沌の眷属', 'Crawling Chaos Spawn', '「現実歪曲」- 周囲の現実を歪め混乱状態発生',
        'ニャルラトホテプに触れられ、常に変化し続ける不安定な存在なの', 'UNIQUE');

-- トリックスター系種族
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('kitsune', '狐族', 'Kitsune', '「幻術」- 幻影で敵を惑わす能力',
        '変化の術を使う狐の妖怪で、いたずら好きだけど知恵も深いの', 'UNIQUE'),
       ('loki_descendant', 'ロキの末裔', 'Loki\'s Descendant', '「因果転換」- 自分と対象の運命を一時入替',
        '北欧神話の悪戯神ロキの血を引く、カオス要素満載の存在なの', 'UNIQUE'),
       ('paradox_being', 'パラドックス体', 'Paradox Being', '「逆転の理」- 1日1回行動結果を完全逆転',
        '存在自体が矛盾に満ちた、理解不能な法則で動く謎の生命体なの', 'UNIQUE'),
       ('fate_jester', '運命の道化', 'Fate\'s Jester', '「混沌の輪」- 完全ランダム効果を発動',
        '運命の糸を撹乱する存在で、予測不能な出来事を引き起こすの', 'UNIQUE');

-- 超レア種族（レジェンド）
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('demigod', 'デミゴッド', 'Demigod', '「神性の顕現」- 危機時に神の力が発動',
        '神の血を引く選ばれし存在で、運命に導かれる種族なの', 'LEGENDARY'),
       ('reincarnator', '転生者', 'Reincarnator', '「前世の記憶」- 特定スキル習得速度2倍',
        '前世の記憶を持ち、成長速度が非常に早い特殊な存在なの', 'LEGENDARY'),
       ('chaos_vessel', '混沌の器', 'Vessel of Chaos', '「運命改変」- 1日1回行動やり直し可能',
        '運命の節目に生まれた、世界の法則を歪める存在なの', 'LEGENDARY');

-- 隠し種族（シークレット）
INSERT INTO races (id, japanese_name, english_name, special_ability, description, rarity)
VALUES ('mimic', 'ミミック', 'Mimic', '「擬態」- 他種族の特殊能力を一時使用可能',
        'どんな姿にも変化できる珍しい変身種族なの', 'SECRET'),
       ('soul_of_machine', '機械の魂', 'Soul of Machine', '「仮想現実」- 周囲環境を一時改変',
        '機械世界から転生した純粋な意識体なの', 'SECRET');

-- 種族ステータス修正値のデータ
INSERT INTO race_status_modifiers (race_id, strength_mod, vitality_mod, intelligence_mod, agility_mod, dexterity_mod,
                                   luck_mod, health_points_mod, magic_points_mod)
VALUES
-- 基本種族（スタンダード）
('human', 5, 5, 5, 5, 5, 5, 0, 0),
('elf', 0, -2, 3, 3, 0, 0, 0, 0),
('dwarf', 2, 3, 0, -2, 0, 0, 0, 0),
('orc', 5, 2, -3, 0, -2, 0, 0, 0),
('halfling', -3, 0, 0, 3, 0, 4, 0, 0),
('dragonkin', 2, 2, 0, 0, 0, -2, 0, 2),

-- トリッキー種族（ユニーク）
('slime', -2, 8, -2, -2, -2, -2, 0, 0),
('spirit', -5, -3, 3, 0, 0, 0, 0, 7),
('android', 4, 4, 2, 0, 0, -5, 0, -3),
('chaos_entity', 0, 0, 0, 0, 0, 0, 0, 0),    -- ランダム変動するため初期値は0
('fae', -3, -5, 0, 3, 0, 2, 0, 5),
('vampire', 3, 0, 2, 3, 0, 0, 0, 0),

-- クトゥルフ神話系種族
('deep_one', 3, 4, 0, -2, 0, 0, 0, 0),
('shoggoth_hybrid', 4, 6, -3, 0, 0, 0, 0, 0),
('elder_blood', 0, 0, 5, 0, 0, 0, 0, 5),
('crawling_chaos', 0, 0, 0, 0, 0, 0, 0, 0),  -- ランダム変動するため初期値は0

-- トリックスター系種族
('kitsune', -2, 0, 3, 4, 0, 2, 0, 0),
('loki_descendant', 0, 0, 0, 0, 5, 0, 0, 0), -- 運の振り幅が大きいため初期値は0
('paradox_being', 0, 0, 0, 0, 0, 0, 0, 0),   -- 特殊な存在のため初期値は0
('fate_jester', 0, 0, 0, 0, 0, 0, 0, 0),     -- 完全ランダムのため初期値は0

-- 超レア種族（レジェンド）
('demigod', 3, 3, 3, 3, 3, 3, 0, 0),
('reincarnator', 0, 0, 0, 0, 0, 0, 0, 0),    -- レベルごとに成長するため初期値は0
('chaos_vessel', 0, 0, 0, 0, 0, 0, 0, 0),    -- ランダムに3能力上昇、2能力低下するため初期値は0

-- 隠し種族（シークレット）
('mimic', -1, -1, -1, -1, -1, -1, 0, 0),     -- 他種族をコピーするため初期値は低め
('soul_of_machine', -3, 0, 10, 0, 0, 0, 0, -3);
