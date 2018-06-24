-------------------------------------------------
-- Export file for user IMS                    --
-- Created by luozhihui on 2018/6/24, 11:02:59 --
-------------------------------------------------

spool IMS.log

prompt
prompt Creating table BASE_TABLE_COL_DEFINE
prompt ====================================
prompt
create table IMS.BASE_TABLE_COL_DEFINE
(
  UUID            VARCHAR2(50) not null,
  FIELD_NAME      VARCHAR2(50),
  FIELD_DESCRIPT  VARCHAR2(500),
  FIELD_CLASS     VARCHAR2(500),
  OBJECT_NAME     VARCHAR2(200),
  COLUMN_NAME     VARCHAR2(500),
  COLUMN_DESCRIPT VARCHAR2(500),
  COLUMN_TYPE     VARCHAR2(500),
  TABLE_NAME      VARCHAR2(50),
  TABLE_DESCRIPT  VARCHAR2(200),
  SORT_INDEX      VARCHAR2(50),
  STATUS          VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.BASE_TABLE_COL_DEFINE
  is '数据库字段映射表';
comment on column IMS.BASE_TABLE_COL_DEFINE.UUID
  is '主键';
comment on column IMS.BASE_TABLE_COL_DEFINE.FIELD_NAME
  is '对象属性';
comment on column IMS.BASE_TABLE_COL_DEFINE.FIELD_DESCRIPT
  is '属性描述';
comment on column IMS.BASE_TABLE_COL_DEFINE.FIELD_CLASS
  is '属性类型，为基本类型和基本类型的封装类';
comment on column IMS.BASE_TABLE_COL_DEFINE.OBJECT_NAME
  is '类名';
comment on column IMS.BASE_TABLE_COL_DEFINE.COLUMN_NAME
  is '数据库字段名';
comment on column IMS.BASE_TABLE_COL_DEFINE.COLUMN_DESCRIPT
  is '数据库字段描述';
comment on column IMS.BASE_TABLE_COL_DEFINE.COLUMN_TYPE
  is '字段类型';
comment on column IMS.BASE_TABLE_COL_DEFINE.TABLE_NAME
  is '数据库表名';
comment on column IMS.BASE_TABLE_COL_DEFINE.TABLE_DESCRIPT
  is '表名描述';
comment on column IMS.BASE_TABLE_COL_DEFINE.SORT_INDEX
  is '排序';
comment on column IMS.BASE_TABLE_COL_DEFINE.STATUS
  is '状态';
alter table IMS.BASE_TABLE_COL_DEFINE
  add constraint PK_BASE_TABLE_COL_DEFINE primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BASE_TABLE_DEFINE
prompt ================================
prompt
create table IMS.BASE_TABLE_DEFINE
(
  TABLE_NAME    VARCHAR2(50) not null,
  DESCRIPT      VARCHAR2(200),
  DATABASE_TYPE VARCHAR2(200),
  ENTITY_NAME   VARCHAR2(100),
  REMARK        VARCHAR2(1000),
  SORT_INDEX    VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.BASE_TABLE_DEFINE
  is '数据库映射表';
comment on column IMS.BASE_TABLE_DEFINE.TABLE_NAME
  is '表名';
comment on column IMS.BASE_TABLE_DEFINE.DESCRIPT
  is '表名描述';
comment on column IMS.BASE_TABLE_DEFINE.DATABASE_TYPE
  is '数据库类型';
comment on column IMS.BASE_TABLE_DEFINE.ENTITY_NAME
  is '对应的实体类名';
comment on column IMS.BASE_TABLE_DEFINE.REMARK
  is '备注';
comment on column IMS.BASE_TABLE_DEFINE.SORT_INDEX
  is '排序';
alter table IMS.BASE_TABLE_DEFINE
  add constraint PK_BASE_TABLE_DEFINE primary key (TABLE_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table DEPINFO_REPORT
prompt =============================
prompt
create table IMS.DEPINFO_REPORT
(
  ID             VARCHAR2(50) not null,
  VERSION        VARCHAR2(50),
  SECTION        VARCHAR2(200),
  WORKER_ID      VARCHAR2(50),
  WORKER         VARCHAR2(200),
  WORK_DATE      DATE,
  DEP_INFO       VARCHAR2(200),
  DCOM_AUDIT_NUM NUMBER,
  DCOM_ERROR_NUM NUMBER,
  DCOM_PASS_RATE NUMBER,
  DP_AUDIT_NUM   NUMBER,
  DP_ERROR_NUM   NUMBER,
  DP_PASS_RATE   NUMBER,
  DCAR_AUDIT_NUM NUMBER,
  DCAR_ERROR_NUM NUMBER,
  DCAR_PASS_RATE NUMBER,
  REMARK         VARCHAR2(1000),
  STATUS         VARCHAR2(50),
  DEL_FLAG       VARCHAR2(50),
  CREATOR_ID     VARCHAR2(50),
  CREATOR        VARCHAR2(200),
  CREATE_TIME    DATE,
  UPDATER_ID     VARCHAR2(50),
  UPDATER        VARCHAR2(200),
  UPDATE_TIME    DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.DEPINFO_REPORT
  is '深度信息周报';
comment on column IMS.DEPINFO_REPORT.ID
  is '主键ID';
comment on column IMS.DEPINFO_REPORT.VERSION
  is '作业版本';
comment on column IMS.DEPINFO_REPORT.SECTION
  is '作业组名称';
comment on column IMS.DEPINFO_REPORT.WORKER_ID
  is '作业员ID';
comment on column IMS.DEPINFO_REPORT.WORKER
  is '作业员';
comment on column IMS.DEPINFO_REPORT.WORK_DATE
  is '作业日期';
comment on column IMS.DEPINFO_REPORT.DEP_INFO
  is '深度信息-通用';
comment on column IMS.DEPINFO_REPORT.DCOM_AUDIT_NUM
  is '深度信息通用质检量';
comment on column IMS.DEPINFO_REPORT.DCOM_ERROR_NUM
  is '深度信息通用错误量';
comment on column IMS.DEPINFO_REPORT.DCOM_PASS_RATE
  is '深度信息通用合格率';
comment on column IMS.DEPINFO_REPORT.DP_AUDIT_NUM
  is '深度信息停车场质检量';
comment on column IMS.DEPINFO_REPORT.DP_ERROR_NUM
  is '深度信息停车场错误量';
comment on column IMS.DEPINFO_REPORT.DP_PASS_RATE
  is '深度信息停车场合格率';
comment on column IMS.DEPINFO_REPORT.DCAR_AUDIT_NUM
  is '深度信息汽车租赁质检量';
comment on column IMS.DEPINFO_REPORT.DCAR_ERROR_NUM
  is '深度信息汽车租赁错误量';
comment on column IMS.DEPINFO_REPORT.DCAR_PASS_RATE
  is '深度信息汽车租赁合格率';
comment on column IMS.DEPINFO_REPORT.REMARK
  is '备注';
comment on column IMS.DEPINFO_REPORT.STATUS
  is '状态';
comment on column IMS.DEPINFO_REPORT.DEL_FLAG
  is '删除标识';
comment on column IMS.DEPINFO_REPORT.CREATOR_ID
  is '创建人ID';
comment on column IMS.DEPINFO_REPORT.CREATOR
  is '创建人';
comment on column IMS.DEPINFO_REPORT.CREATE_TIME
  is '创建时间';
comment on column IMS.DEPINFO_REPORT.UPDATER_ID
  is '更新人ID';
comment on column IMS.DEPINFO_REPORT.UPDATER
  is '更新人';
comment on column IMS.DEPINFO_REPORT.UPDATE_TIME
  is '更新时间';
alter table IMS.DEPINFO_REPORT
  add constraint PK_DEPINFO_REPORT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table DIARY_INFO
prompt =========================
prompt
create table IMS.DIARY_INFO
(
  ID               VARCHAR2(50) not null,
  EMP_ID           VARCHAR2(50),
  EMP_NAME         VARCHAR2(200),
  SECTION          VARCHAR2(200),
  WORK_DATE        DATE,
  WORK_HOURS       NUMBER,
  WORK_LOAD        NUMBER,
  WORK_TYPE        VARCHAR2(50),
  TASK_TYPE        VARCHAR2(50),
  TASK_PROVINCE    VARCHAR2(500),
  TASK_LEVEL       VARCHAR2(50),
  TASK_CODE        VARCHAR2(50),
  TASK_NAME        VARCHAR2(200),
  OTHERS_ITEM      VARCHAR2(200),
  PRODUCE_PROVINCE VARCHAR2(50),
  REMARK           VARCHAR2(1000),
  STATUS           VARCHAR2(50),
  DEL_FLAG         VARCHAR2(50),
  CREATOR_ID       VARCHAR2(50),
  CREATOR          VARCHAR2(200),
  CREATE_TIME      DATE,
  UPDATER_ID       VARCHAR2(50),
  UPDATER          VARCHAR2(200),
  UPDATE_TIME      DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.DIARY_INFO
  is '日志信息表';
comment on column IMS.DIARY_INFO.ID
  is 'ID主键';
comment on column IMS.DIARY_INFO.EMP_ID
  is '员工编号';
comment on column IMS.DIARY_INFO.EMP_NAME
  is '员工姓名';
comment on column IMS.DIARY_INFO.SECTION
  is '项目组';
comment on column IMS.DIARY_INFO.WORK_DATE
  is '工作日期';
comment on column IMS.DIARY_INFO.WORK_HOURS
  is '工作时长';
comment on column IMS.DIARY_INFO.WORK_LOAD
  is '工作量';
comment on column IMS.DIARY_INFO.WORK_TYPE
  is '工作类型';
comment on column IMS.DIARY_INFO.TASK_TYPE
  is '任务类型';
comment on column IMS.DIARY_INFO.TASK_PROVINCE
  is '任务省份';
comment on column IMS.DIARY_INFO.TASK_LEVEL
  is '任务等级';
comment on column IMS.DIARY_INFO.TASK_CODE
  is '任务编号';
comment on column IMS.DIARY_INFO.TASK_NAME
  is '任务名称';
comment on column IMS.DIARY_INFO.OTHERS_ITEM
  is '其它子项';
comment on column IMS.DIARY_INFO.PRODUCE_PROVINCE
  is '出品省份';
comment on column IMS.DIARY_INFO.REMARK
  is '备注';
comment on column IMS.DIARY_INFO.STATUS
  is '状态';
comment on column IMS.DIARY_INFO.DEL_FLAG
  is '删除标识';
comment on column IMS.DIARY_INFO.CREATOR_ID
  is '创建人ID';
comment on column IMS.DIARY_INFO.CREATOR
  is '创建人';
comment on column IMS.DIARY_INFO.CREATE_TIME
  is '创建时间';
comment on column IMS.DIARY_INFO.UPDATER_ID
  is '更新人ID';
comment on column IMS.DIARY_INFO.UPDATER
  is '更新人';
comment on column IMS.DIARY_INFO.UPDATE_TIME
  is '更新时间';
alter table IMS.DIARY_INFO
  add constraint PK_DIARY_INFO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP
prompt ==================
prompt
create table IMS.EMP
(
  EMP_ID        VARCHAR2(50) not null,
  EMP_NAME      VARCHAR2(200),
  EMP_SEX       VARCHAR2(10),
  EMP_HIREDATE  DATE,
  RANK          VARCHAR2(200),
  EMP_ENTRY_AGE NUMBER(2),
  EMP_DEPT      VARCHAR2(100),
  EMP_SEC       VARCHAR2(100),
  EMP_POST      VARCHAR2(100),
  EMP_EMAIL     VARCHAR2(50),
  EMP_TEL       VARCHAR2(20),
  EMP_ADDRESS   VARCHAR2(500),
  REMARK        VARCHAR2(1000),
  STATUS        VARCHAR2(200),
  CREATOR_ID    VARCHAR2(50),
  CREATOR       VARCHAR2(200),
  CREATE_TIME   DATE,
  UPDATER_ID    VARCHAR2(50),
  UPDATER       VARCHAR2(200),
  UPDATE_TIME   DATE,
  ATTR1         VARCHAR2(200),
  ATTR2         VARCHAR2(200),
  ATTR3         VARCHAR2(200),
  ATTR4         VARCHAR2(200),
  ATTR5         VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.EMP
  is '员工表';
comment on column IMS.EMP.EMP_ID
  is '员工编号';
comment on column IMS.EMP.EMP_NAME
  is '员工名称';
comment on column IMS.EMP.EMP_SEX
  is '员工性别';
comment on column IMS.EMP.EMP_HIREDATE
  is '入职时间';
comment on column IMS.EMP.RANK
  is '职级';
comment on column IMS.EMP.EMP_ENTRY_AGE
  is '员工司龄';
comment on column IMS.EMP.EMP_DEPT
  is '所属部门';
comment on column IMS.EMP.EMP_SEC
  is '所在科室';
comment on column IMS.EMP.EMP_POST
  is '职务名称';
comment on column IMS.EMP.EMP_EMAIL
  is '员工邮箱';
comment on column IMS.EMP.EMP_TEL
  is '联系方式';
comment on column IMS.EMP.EMP_ADDRESS
  is '现住地址';
comment on column IMS.EMP.REMARK
  is '备注';
comment on column IMS.EMP.STATUS
  is '状态';
comment on column IMS.EMP.CREATOR_ID
  is '创建人ID';
comment on column IMS.EMP.CREATOR
  is '创建人';
comment on column IMS.EMP.CREATE_TIME
  is '创建时间';
comment on column IMS.EMP.UPDATER_ID
  is '更新人ID';
comment on column IMS.EMP.UPDATER
  is '更新人';
comment on column IMS.EMP.UPDATE_TIME
  is '更新时间';
comment on column IMS.EMP.ATTR1
  is 'attr1';
comment on column IMS.EMP.ATTR2
  is 'attr2';
comment on column IMS.EMP.ATTR3
  is 'attr3';
comment on column IMS.EMP.ATTR4
  is 'attr4';
comment on column IMS.EMP.ATTR5
  is 'attr5';
alter table IMS.EMP
  add constraint PK_EMP primary key (EMP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP_APTITUDE
prompt ===========================
prompt
create table IMS.EMP_APTITUDE
(
  ID              VARCHAR2(50) not null,
  VERSION         VARCHAR2(50),
  EMP_ID          VARCHAR2(50),
  EMP_NAME        VARCHAR2(200),
  SECTION         VARCHAR2(200),
  PROFESSION_TYPE VARCHAR2(200),
  WORK_APTITUDE   VARCHAR2(50),
  AUDIT_APTITUDE  VARCHAR2(50),
  REMARK          VARCHAR2(1000),
  STATUS          VARCHAR2(50),
  DEL_FLAG        VARCHAR2(50),
  CREATOR_ID      VARCHAR2(50),
  CREATOR         VARCHAR2(200),
  CREATE_TIME     DATE,
  UPDATER_ID      VARCHAR2(50),
  UPDATER         VARCHAR2(200),
  UPDATE_TIME     DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.EMP_APTITUDE
  is '人员资质表';
comment on column IMS.EMP_APTITUDE.ID
  is '主键ID';
comment on column IMS.EMP_APTITUDE.VERSION
  is '作业版本';
comment on column IMS.EMP_APTITUDE.EMP_ID
  is '员工编号';
comment on column IMS.EMP_APTITUDE.EMP_NAME
  is '员工姓名';
comment on column IMS.EMP_APTITUDE.SECTION
  is '项目组';
comment on column IMS.EMP_APTITUDE.PROFESSION_TYPE
  is '业务类型';
comment on column IMS.EMP_APTITUDE.WORK_APTITUDE
  is '作业资质';
comment on column IMS.EMP_APTITUDE.AUDIT_APTITUDE
  is '质检资质';
comment on column IMS.EMP_APTITUDE.REMARK
  is '备注';
comment on column IMS.EMP_APTITUDE.STATUS
  is '状态';
comment on column IMS.EMP_APTITUDE.DEL_FLAG
  is '删除标识';
comment on column IMS.EMP_APTITUDE.CREATOR_ID
  is '创建人ID';
comment on column IMS.EMP_APTITUDE.CREATOR
  is '创建人';
comment on column IMS.EMP_APTITUDE.CREATE_TIME
  is '创建时间';
comment on column IMS.EMP_APTITUDE.UPDATER_ID
  is '更新人ID';
comment on column IMS.EMP_APTITUDE.UPDATER
  is '更新人';
comment on column IMS.EMP_APTITUDE.UPDATE_TIME
  is '更新时间';
alter table IMS.EMP_APTITUDE
  add constraint PK_EMP_APTITUDE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP_APTITUDE_BAK
prompt ===============================
prompt
create table IMS.EMP_APTITUDE_BAK
(
  ID              VARCHAR2(50),
  VERSION         VARCHAR2(50),
  EMP_ID          VARCHAR2(50),
  EMP_NAME        VARCHAR2(200),
  PROFESSION_TYPE VARCHAR2(200),
  WORK_APTITUDE   VARCHAR2(50),
  AUDIT_APTITUDE  VARCHAR2(50),
  REMARK          VARCHAR2(1000),
  STATUS          VARCHAR2(50),
  DEL_FLAG        VARCHAR2(50),
  CREATOR_ID      VARCHAR2(50),
  CREATOR         VARCHAR2(200),
  CREATE_TIME     DATE,
  UPDATER_ID      VARCHAR2(50),
  UPDATER         VARCHAR2(200),
  UPDATE_TIME     DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP_BACK
prompt =======================
prompt
create table IMS.EMP_BACK
(
  EMP_ID        VARCHAR2(50) not null,
  EMP_NAME      VARCHAR2(200),
  EMP_SEX       VARCHAR2(10),
  EMP_HIREDATE  DATE,
  EMP_ENTRY_AGE NUMBER(2),
  EMP_DEPT      VARCHAR2(100),
  EMP_SEC       VARCHAR2(100),
  EMP_POST      VARCHAR2(100),
  EMP_EMAIL     VARCHAR2(50),
  EMP_TEL       VARCHAR2(20),
  CREATE_TIME   DATE,
  UPDATE_TIME   DATE,
  ATTR1         VARCHAR2(200),
  ATTR2         VARCHAR2(200),
  ATTR3         VARCHAR2(200),
  ATTR4         VARCHAR2(200),
  ATTR5         VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP_RESUME
prompt =========================
prompt
create table IMS.EMP_RESUME
(
  RESUME_ID   VARCHAR2(50) not null,
  EMP_ID      VARCHAR2(50),
  EMP_NAME    VARCHAR2(200),
  SPECIALITY  VARCHAR2(4000),
  CERTIFICATE VARCHAR2(4000),
  AWARD       VARCHAR2(4000),
  TRAINING    VARCHAR2(4000),
  PROJECT     VARCHAR2(4000),
  WORK_RECORD VARCHAR2(4000),
  CREATE_TIME DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column IMS.EMP_RESUME.EMP_ID
  is '员工编号';
comment on column IMS.EMP_RESUME.EMP_NAME
  is '员工姓名';
comment on column IMS.EMP_RESUME.SPECIALITY
  is '专长及特长';
comment on column IMS.EMP_RESUME.CERTIFICATE
  is '认证及证书';
comment on column IMS.EMP_RESUME.AWARD
  is '奖励情况';
comment on column IMS.EMP_RESUME.TRAINING
  is '培训情况';
comment on column IMS.EMP_RESUME.PROJECT
  is '项目经验';
comment on column IMS.EMP_RESUME.WORK_RECORD
  is '工作履历';
comment on column IMS.EMP_RESUME.CREATE_TIME
  is '创建时间';
alter table IMS.EMP_RESUME
  add constraint PK_EMP_RESUME primary key (RESUME_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FILE_INFO
prompt ========================
prompt
create table IMS.FILE_INFO
(
  UUID          VARCHAR2(50) not null,
  OBJECT_ID     VARCHAR2(50),
  MODULE_ID     VARCHAR2(50),
  FILE_NAME     VARCHAR2(500),
  OLD_FILE_NAME VARCHAR2(500),
  FILE_EXNAME   VARCHAR2(100),
  FILE_TYPE     VARCHAR2(100),
  FILE_SIZE     VARCHAR2(50),
  UPLOAD_DATE   DATE,
  FILE_PATH     VARCHAR2(200),
  DESCRIPTION   VARCHAR2(2000),
  REMARK        VARCHAR2(2000),
  SORT_INDEX    VARCHAR2(100),
  STATUS        VARCHAR2(10),
  DEL_FLAG      VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.FILE_INFO
  is '上传文件信息';
comment on column IMS.FILE_INFO.UUID
  is '主键';
comment on column IMS.FILE_INFO.OBJECT_ID
  is '所属ID';
comment on column IMS.FILE_INFO.MODULE_ID
  is '模块ID';
comment on column IMS.FILE_INFO.FILE_NAME
  is '文件名';
comment on column IMS.FILE_INFO.OLD_FILE_NAME
  is '旧文件名';
comment on column IMS.FILE_INFO.FILE_EXNAME
  is '文件扩展名';
comment on column IMS.FILE_INFO.FILE_TYPE
  is '文件类型';
comment on column IMS.FILE_INFO.FILE_SIZE
  is '文件大小';
comment on column IMS.FILE_INFO.UPLOAD_DATE
  is '上传日期';
comment on column IMS.FILE_INFO.FILE_PATH
  is '文件路径';
comment on column IMS.FILE_INFO.DESCRIPTION
  is '文件描述';
comment on column IMS.FILE_INFO.REMARK
  is '备注';
comment on column IMS.FILE_INFO.SORT_INDEX
  is '排序';
comment on column IMS.FILE_INFO.STATUS
  is '状态';
comment on column IMS.FILE_INFO.DEL_FLAG
  is '删除标识';
alter table IMS.FILE_INFO
  add constraint PK_FILE_INFO primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FIXED_ASSET
prompt ==========================
prompt
create table IMS.FIXED_ASSET
(
  UUID          VARCHAR2(50) not null,
  ASSET_NUMBER  VARCHAR2(200),
  ASSET_NAME    VARCHAR2(500),
  STANDARD      VARCHAR2(500),
  ASSET_CLASS   VARCHAR2(500),
  ASSET_USER_ID VARCHAR2(500),
  ASSET_USER    VARCHAR2(200),
  SECTION       VARCHAR2(200),
  USE_STATE     VARCHAR2(200),
  STORE_PLACE   VARCHAR2(500),
  START_DATE    DATE,
  REMARK        VARCHAR2(1000),
  STATUS        VARCHAR2(50),
  DEL_FLAG      VARCHAR2(50),
  CREATOR_ID    VARCHAR2(50),
  CREATOR       VARCHAR2(200),
  CREATE_TIME   DATE,
  UPDATER_ID    VARCHAR2(50),
  UPDATER       VARCHAR2(200),
  UPDATE_TIME   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.FIXED_ASSET
  is '固定资产表';
comment on column IMS.FIXED_ASSET.UUID
  is '表主键';
comment on column IMS.FIXED_ASSET.ASSET_NUMBER
  is '资产编号';
comment on column IMS.FIXED_ASSET.ASSET_NAME
  is '资产名称';
comment on column IMS.FIXED_ASSET.STANDARD
  is '规格';
comment on column IMS.FIXED_ASSET.ASSET_CLASS
  is '资产类别';
comment on column IMS.FIXED_ASSET.ASSET_USER
  is '使用人';
comment on column IMS.FIXED_ASSET.SECTION
  is '使用科室';
comment on column IMS.FIXED_ASSET.USE_STATE
  is '使用状态';
comment on column IMS.FIXED_ASSET.STORE_PLACE
  is '存放地点';
comment on column IMS.FIXED_ASSET.START_DATE
  is '开始使用日期';
comment on column IMS.FIXED_ASSET.REMARK
  is '备注';
comment on column IMS.FIXED_ASSET.STATUS
  is '状态';
comment on column IMS.FIXED_ASSET.DEL_FLAG
  is '删除标识';
comment on column IMS.FIXED_ASSET.CREATOR_ID
  is '创建人ID';
comment on column IMS.FIXED_ASSET.CREATOR
  is '创建人';
comment on column IMS.FIXED_ASSET.CREATE_TIME
  is '创建时间';
comment on column IMS.FIXED_ASSET.UPDATER_ID
  is '更新人ID';
comment on column IMS.FIXED_ASSET.UPDATER
  is '更新人';
comment on column IMS.FIXED_ASSET.UPDATE_TIME
  is '更新时间';
alter table IMS.FIXED_ASSET
  add constraint PK_FIXED_ASSET primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MENU
prompt ===================
prompt
create table IMS.MENU
(
  ID         VARCHAR2(10),
  NAME       VARCHAR2(200),
  PID        VARCHAR2(10),
  SOURCE_URL VARCHAR2(100),
  CLS_NAME   VARCHAR2(200),
  ATTR1      VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MODULE_PERMISSION_REL
prompt ====================================
prompt
create table IMS.MODULE_PERMISSION_REL
(
  REL_ID        VARCHAR2(50) not null,
  MODULE_ID     VARCHAR2(50),
  PERMISSION_ID VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.MODULE_PERMISSION_REL
  is '模块权限关联的表，其关联关系是一对多';
alter table IMS.MODULE_PERMISSION_REL
  add constraint PK_MODULE_PERMISSION_REL primary key (REL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MONITOR_INFO
prompt ===========================
prompt
create table IMS.MONITOR_INFO
(
  ID           VARCHAR2(50) not null,
  VERSION      VARCHAR2(50),
  WORK_TYPE    VARCHAR2(200),
  SECTION      VARCHAR2(200),
  WORKER_ID    VARCHAR2(50),
  WORKER       VARCHAR2(200),
  WORK_DATE    DATE,
  MONITOR_NUM  NUMBER,
  M_ERROR_NUM  NUMBER,
  M_PASS_RATE  NUMBER,
  MONITOR_ID   VARCHAR2(50),
  MONITOR      VARCHAR2(200),
  MONITOR_DATE DATE,
  REMARK       VARCHAR2(1000),
  STATUS       VARCHAR2(50),
  DEL_FLAG     VARCHAR2(50),
  CREATOR_ID   VARCHAR2(50),
  CREATOR      VARCHAR2(200),
  CREATE_TIME  DATE,
  UPDATER_ID   VARCHAR2(50),
  UPDATER      VARCHAR2(200),
  UPDATE_TIME  DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.MONITOR_INFO
  is '监察统计表';
comment on column IMS.MONITOR_INFO.ID
  is '主键ID';
comment on column IMS.MONITOR_INFO.VERSION
  is '作业版本';
comment on column IMS.MONITOR_INFO.WORK_TYPE
  is '作业类型';
comment on column IMS.MONITOR_INFO.SECTION
  is '项目组';
comment on column IMS.MONITOR_INFO.WORKER_ID
  is '作业员ID';
comment on column IMS.MONITOR_INFO.WORKER
  is '作业员';
comment on column IMS.MONITOR_INFO.WORK_DATE
  is '作业日期';
comment on column IMS.MONITOR_INFO.MONITOR_NUM
  is '监察量';
comment on column IMS.MONITOR_INFO.M_ERROR_NUM
  is '监察错误量';
comment on column IMS.MONITOR_INFO.M_PASS_RATE
  is '监察合格率';
comment on column IMS.MONITOR_INFO.MONITOR_ID
  is '监察员ID';
comment on column IMS.MONITOR_INFO.MONITOR
  is '监察员';
comment on column IMS.MONITOR_INFO.MONITOR_DATE
  is '监察日期';
comment on column IMS.MONITOR_INFO.REMARK
  is '备注';
comment on column IMS.MONITOR_INFO.STATUS
  is '状态';
comment on column IMS.MONITOR_INFO.DEL_FLAG
  is '删除标识';
comment on column IMS.MONITOR_INFO.CREATOR_ID
  is '创建人ID';
comment on column IMS.MONITOR_INFO.CREATOR
  is '创建人';
comment on column IMS.MONITOR_INFO.CREATE_TIME
  is '创建时间';
comment on column IMS.MONITOR_INFO.UPDATER_ID
  is '更新人ID';
comment on column IMS.MONITOR_INFO.UPDATER
  is '更新人';
comment on column IMS.MONITOR_INFO.UPDATE_TIME
  is '更新时间';
alter table IMS.MONITOR_INFO
  add constraint PK_MONITOR_INFO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table POI_QUESTION
prompt ===========================
prompt
create table IMS.POI_QUESTION
(
  ID               VARCHAR2(50) not null,
  VERSION          VARCHAR2(50),
  TASK_CODE        VARCHAR2(50),
  TASK_NAME        VARCHAR2(200),
  WORK_TYPE        VARCHAR2(200),
  FID              VARCHAR2(50),
  ITEM             VARCHAR2(200),
  ITEM_TYPE        VARCHAR2(200),
  FINE_TYPE        VARCHAR2(200),
  ERROR_CONTENT    VARCHAR2(2000),
  ERROR_TYPE       VARCHAR2(200),
  ERROR_LEVEL      VARCHAR2(50),
  DESCRIPTION      VARCHAR2(2000),
  RIGHT_CONTENT    VARCHAR2(2000),
  ORIGINAL_CONTENT VARCHAR2(2000),
  SECTION          VARCHAR2(50),
  WORKER_ID        VARCHAR2(50),
  WORKER           VARCHAR2(200),
  WORK_TIME        DATE,
  AUDITOR_ID       VARCHAR2(50),
  AUDITOR          VARCHAR2(200),
  AUDIT_TIME       DATE,
  STAT_DATE        DATE,
  REMARK           VARCHAR2(1000),
  STATUS           VARCHAR2(50),
  DEL_FLAG         VARCHAR2(50),
  CREATOR_ID       VARCHAR2(50),
  CREATOR          VARCHAR2(200),
  CREATE_TIME      DATE,
  UPDATER_ID       VARCHAR2(50),
  UPDATER          VARCHAR2(50),
  UPDATE_TIME      DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.POI_QUESTION
  is '设施品质周报问题表';
comment on column IMS.POI_QUESTION.ID
  is '主键ID';
comment on column IMS.POI_QUESTION.VERSION
  is '作业版本';
comment on column IMS.POI_QUESTION.TASK_CODE
  is '任务编号';
comment on column IMS.POI_QUESTION.TASK_NAME
  is '任务名称';
comment on column IMS.POI_QUESTION.WORK_TYPE
  is '作业对象';
comment on column IMS.POI_QUESTION.FID
  is 'fid';
comment on column IMS.POI_QUESTION.ITEM
  is '作业项目（大分类）';
comment on column IMS.POI_QUESTION.ITEM_TYPE
  is '项目类型（中分类）';
comment on column IMS.POI_QUESTION.FINE_TYPE
  is '详细检查项（子分类）';
comment on column IMS.POI_QUESTION.ERROR_CONTENT
  is '错误内容';
comment on column IMS.POI_QUESTION.ERROR_TYPE
  is '错误类型';
comment on column IMS.POI_QUESTION.ERROR_LEVEL
  is '问题等级';
comment on column IMS.POI_QUESTION.DESCRIPTION
  is '问题描述';
comment on column IMS.POI_QUESTION.RIGHT_CONTENT
  is '正确内容';
comment on column IMS.POI_QUESTION.ORIGINAL_CONTENT
  is '原始信息';
comment on column IMS.POI_QUESTION.SECTION
  is '项目组';
comment on column IMS.POI_QUESTION.WORKER_ID
  is '作业员ID';
comment on column IMS.POI_QUESTION.WORKER
  is '作业员';
comment on column IMS.POI_QUESTION.WORK_TIME
  is '作业时间';
comment on column IMS.POI_QUESTION.AUDITOR_ID
  is '质检员ID';
comment on column IMS.POI_QUESTION.AUDITOR
  is '质检员';
comment on column IMS.POI_QUESTION.AUDIT_TIME
  is '质检时间';
comment on column IMS.POI_QUESTION.STAT_DATE
  is '统计日期';
comment on column IMS.POI_QUESTION.REMARK
  is '备注';
comment on column IMS.POI_QUESTION.STATUS
  is '状态';
comment on column IMS.POI_QUESTION.DEL_FLAG
  is '删除标识';
comment on column IMS.POI_QUESTION.CREATOR_ID
  is '创建人ID';
comment on column IMS.POI_QUESTION.CREATOR
  is '创建人';
comment on column IMS.POI_QUESTION.CREATE_TIME
  is '创建时间';
comment on column IMS.POI_QUESTION.UPDATER_ID
  is '更新人ID';
comment on column IMS.POI_QUESTION.UPDATER
  is '更新人';
comment on column IMS.POI_QUESTION.UPDATE_TIME
  is '更新时间';
alter table IMS.POI_QUESTION
  add constraint PK_POI_QUESTION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table POI_REPORT
prompt =========================
prompt
create table IMS.POI_REPORT
(
  ID           VARCHAR2(50) not null,
  VERSION      VARCHAR2(50),
  WORKER_ID    VARCHAR2(50),
  WORKER       VARCHAR2(200),
  SECTION      VARCHAR2(200),
  WORK_DATE    DATE,
  STAT_DATE    DATE,
  CP_AUDIT_NUM NUMBER,
  CP_ERROR_NUM NUMBER,
  CP_PASS_RATE NUMBER,
  CA_AUDIT_NUM NUMBER,
  CA_ERROR_NUM NUMBER,
  CA_PASS_RATE NUMBER,
  EP_AUDIT_NUM NUMBER,
  EP_ERROR_NUM NUMBER,
  EP_PASS_RATE NUMBER,
  EA_AUDIT_NUM NUMBER,
  EA_ERROR_NUM NUMBER,
  EA_PASS_NUM  NUMBER,
  REMARK       VARCHAR2(1000),
  STATUS       VARCHAR2(50),
  DEL_FLAG     VARCHAR2(50),
  CREATOR_ID   VARCHAR2(50),
  CREATOR      VARCHAR2(200),
  CREATE_TIME  DATE,
  UPDATER_ID   VARCHAR2(50),
  UPDATER      VARCHAR2(200),
  UPDATE_TIME  DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.POI_REPORT
  is '设施品质周报';
comment on column IMS.POI_REPORT.ID
  is '主键ID';
comment on column IMS.POI_REPORT.VERSION
  is '作业版本';
comment on column IMS.POI_REPORT.WORKER_ID
  is '作业员ID';
comment on column IMS.POI_REPORT.WORKER
  is '作业员';
comment on column IMS.POI_REPORT.SECTION
  is '作业组名称';
comment on column IMS.POI_REPORT.WORK_DATE
  is '作业日期';
comment on column IMS.POI_REPORT.STAT_DATE
  is '统计日期';
comment on column IMS.POI_REPORT.CP_AUDIT_NUM
  is '中文名称质检量';
comment on column IMS.POI_REPORT.CP_ERROR_NUM
  is '中文名称错误量';
comment on column IMS.POI_REPORT.CP_PASS_RATE
  is '中文名称合格率';
comment on column IMS.POI_REPORT.CA_AUDIT_NUM
  is '中文地址质检量';
comment on column IMS.POI_REPORT.CA_ERROR_NUM
  is '中文地址错误量';
comment on column IMS.POI_REPORT.CA_PASS_RATE
  is '中文地址合格率';
comment on column IMS.POI_REPORT.EP_AUDIT_NUM
  is '英文名称质检量';
comment on column IMS.POI_REPORT.EP_ERROR_NUM
  is '英文名称错误量';
comment on column IMS.POI_REPORT.EP_PASS_RATE
  is '英文名称合格率';
comment on column IMS.POI_REPORT.EA_AUDIT_NUM
  is '英文地址质检量';
comment on column IMS.POI_REPORT.EA_ERROR_NUM
  is '英文地址错误量';
comment on column IMS.POI_REPORT.EA_PASS_NUM
  is '英文地址合格率';
comment on column IMS.POI_REPORT.REMARK
  is '备注';
comment on column IMS.POI_REPORT.STATUS
  is '状态';
comment on column IMS.POI_REPORT.DEL_FLAG
  is '删除标识';
comment on column IMS.POI_REPORT.CREATOR_ID
  is '创建人ID';
comment on column IMS.POI_REPORT.CREATOR
  is '创建人';
comment on column IMS.POI_REPORT.CREATE_TIME
  is '创建时间';
comment on column IMS.POI_REPORT.UPDATER_ID
  is '更新人ID';
comment on column IMS.POI_REPORT.UPDATER
  is '更新人';
comment on column IMS.POI_REPORT.UPDATE_TIME
  is '更新时间';
alter table IMS.POI_REPORT
  add constraint PK_POI_REPORT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRE_GRADE
prompt ========================
prompt
create table IMS.PRE_GRADE
(
  ID          VARCHAR2(50) not null,
  VERSION     VARCHAR2(50),
  EMP_ID      VARCHAR2(50),
  EMP_NAME    VARCHAR2(200),
  SECTION     VARCHAR2(200),
  GRADE       NUMBER,
  SEC_GRADE   NUMBER,
  TEST_DATE   DATE,
  CREATE_TIME DATE,
  CREATOR_ID  VARCHAR2(50),
  CREATOR     VARCHAR2(200),
  UPDATE_TIME DATE,
  UPDATER_ID  VARCHAR2(50),
  UPDATER     VARCHAR2(200),
  STATUS      VARCHAR2(10),
  REMARK      VARCHAR2(2000),
  DEL_FLAG    VARCHAR2(20),
  ATTR1       VARCHAR2(200),
  ATTR2       VARCHAR2(200),
  ATTR3       VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.PRE_GRADE
  is '版本前考核成绩表';
comment on column IMS.PRE_GRADE.ID
  is '主键';
comment on column IMS.PRE_GRADE.VERSION
  is '版本号';
comment on column IMS.PRE_GRADE.EMP_ID
  is '员工编号';
comment on column IMS.PRE_GRADE.EMP_NAME
  is '员工姓名';
comment on column IMS.PRE_GRADE.SECTION
  is '所属科室';
comment on column IMS.PRE_GRADE.GRADE
  is '考核成绩';
comment on column IMS.PRE_GRADE.SEC_GRADE
  is '补考成绩';
comment on column IMS.PRE_GRADE.TEST_DATE
  is '考核日期';
comment on column IMS.PRE_GRADE.CREATE_TIME
  is '创建时间';
comment on column IMS.PRE_GRADE.CREATOR_ID
  is '创建人ID';
comment on column IMS.PRE_GRADE.CREATOR
  is '创建人';
comment on column IMS.PRE_GRADE.UPDATE_TIME
  is '更新时间';
comment on column IMS.PRE_GRADE.UPDATER_ID
  is '更新人ID';
comment on column IMS.PRE_GRADE.UPDATER
  is '更新人';
comment on column IMS.PRE_GRADE.STATUS
  is '状态';
comment on column IMS.PRE_GRADE.REMARK
  is '备注';
comment on column IMS.PRE_GRADE.DEL_FLAG
  is '删除标识';
comment on column IMS.PRE_GRADE.ATTR1
  is 'attr1';
comment on column IMS.PRE_GRADE.ATTR2
  is 'attr2';
comment on column IMS.PRE_GRADE.ATTR3
  is 'attr3';
alter table IMS.PRE_GRADE
  add constraint PK_PRE_GRADE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRO_ABILITY
prompt ==========================
prompt
create table IMS.PRO_ABILITY
(
  UUID         VARCHAR2(50) not null,
  VERSION      VARCHAR2(50),
  EMP_ID       VARCHAR2(50),
  EMP_NAME     VARCHAR2(200),
  SECTION      VARCHAR2(200),
  PRO_STAGE    VARCHAR2(50),
  PRO_TYPE     VARCHAR2(50),
  LOG_ABILITY  VARCHAR2(50),
  TEST_ABILITY VARCHAR2(50),
  REMARK       VARCHAR2(1000),
  STATUS       VARCHAR2(50),
  DEL_FLAG     VARCHAR2(50),
  CREATOR_ID   VARCHAR2(50),
  CREATOR      VARCHAR2(200),
  CREATE_TIME  DATE,
  UPDATER_ID   VARCHAR2(50),
  UPDATER      VARCHAR2(200),
  UPDATE_TIME  DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.PRO_ABILITY
  is '业务能力表';
comment on column IMS.PRO_ABILITY.UUID
  is '主键';
comment on column IMS.PRO_ABILITY.VERSION
  is '版本号';
comment on column IMS.PRO_ABILITY.EMP_ID
  is '员工编号';
comment on column IMS.PRO_ABILITY.EMP_NAME
  is '员工姓名';
comment on column IMS.PRO_ABILITY.SECTION
  is '项目组';
comment on column IMS.PRO_ABILITY.PRO_STAGE
  is '业务阶段';
comment on column IMS.PRO_ABILITY.PRO_TYPE
  is '业务类型';
comment on column IMS.PRO_ABILITY.LOG_ABILITY
  is '录入能力';
comment on column IMS.PRO_ABILITY.TEST_ABILITY
  is '检验能力';
comment on column IMS.PRO_ABILITY.REMARK
  is '备注';
comment on column IMS.PRO_ABILITY.STATUS
  is '状态';
comment on column IMS.PRO_ABILITY.DEL_FLAG
  is '删除标识';
comment on column IMS.PRO_ABILITY.CREATOR_ID
  is '创建人ID';
comment on column IMS.PRO_ABILITY.CREATOR
  is '创建人';
comment on column IMS.PRO_ABILITY.CREATE_TIME
  is '创建时间';
comment on column IMS.PRO_ABILITY.UPDATER_ID
  is '更新人ID';
comment on column IMS.PRO_ABILITY.UPDATER
  is '更新人';
comment on column IMS.PRO_ABILITY.UPDATE_TIME
  is '更新时间';
alter table IMS.PRO_ABILITY
  add constraint PK_PRO_ABILITY primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table QUESTION_SOURCE
prompt ==============================
prompt
create table IMS.QUESTION_SOURCE
(
  ID                VARCHAR2(50) not null,
  VERSION           VARCHAR2(100),
  EMP_ID            VARCHAR2(50),
  EMP_NAME          VARCHAR2(200),
  SECTION           VARCHAR2(200),
  ERROR_DESCRIPTION VARCHAR2(4000),
  CHECK_RESULT      VARCHAR2(4000),
  OCCUR_REASON      VARCHAR2(4000),
  REMARK            VARCHAR2(1000),
  STATUS            VARCHAR2(50),
  DEL_FLAG          VARCHAR2(50),
  CREATOR_ID        VARCHAR2(50),
  CREATOR           VARCHAR2(200),
  CREATE_TIME       DATE,
  UPDATER_ID        VARCHAR2(50),
  UPDATER           VARCHAR2(200),
  UPDATE_TIME       DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.QUESTION_SOURCE
  is '问题溯源表';
comment on column IMS.QUESTION_SOURCE.ID
  is '主键';
comment on column IMS.QUESTION_SOURCE.VERSION
  is '版本号';
comment on column IMS.QUESTION_SOURCE.EMP_ID
  is '员工编号';
comment on column IMS.QUESTION_SOURCE.EMP_NAME
  is '员工姓名';
comment on column IMS.QUESTION_SOURCE.SECTION
  is '科室';
comment on column IMS.QUESTION_SOURCE.ERROR_DESCRIPTION
  is '问题描述';
comment on column IMS.QUESTION_SOURCE.CHECK_RESULT
  is '问题调查结果';
comment on column IMS.QUESTION_SOURCE.OCCUR_REASON
  is '问题发生原因';
comment on column IMS.QUESTION_SOURCE.REMARK
  is '备注';
comment on column IMS.QUESTION_SOURCE.STATUS
  is '状态';
comment on column IMS.QUESTION_SOURCE.DEL_FLAG
  is '删除标识';
comment on column IMS.QUESTION_SOURCE.CREATOR_ID
  is '创建人ID';
comment on column IMS.QUESTION_SOURCE.CREATOR
  is '创建人';
comment on column IMS.QUESTION_SOURCE.CREATE_TIME
  is '创建时间';
comment on column IMS.QUESTION_SOURCE.UPDATER_ID
  is '更新人ID';
comment on column IMS.QUESTION_SOURCE.UPDATER
  is '更新人';
comment on column IMS.QUESTION_SOURCE.UPDATE_TIME
  is '更新时间';
alter table IMS.QUESTION_SOURCE
  add constraint PK_QUESTION_SOURCE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROAD_QUESTION
prompt ============================
prompt
create table IMS.ROAD_QUESTION
(
  ID               VARCHAR2(50) not null,
  VERSION          VARCHAR2(50),
  PARENT_TASK_CODE VARCHAR2(50),
  PARENT_TASK_NAME VARCHAR2(200),
  TASK_CODE        VARCHAR2(50),
  TASK_NAME        VARCHAR2(200),
  SECTION          VARCHAR2(200),
  WORKER_ID        VARCHAR2(50),
  WORKER           VARCHAR2(200),
  WORK_DATE        DATE,
  AUDITOR_ID       VARCHAR2(50),
  AUDITOR          VARCHAR2(200),
  AUDIT_DATE       DATE,
  MAP_CODE         VARCHAR2(500),
  FEATURE          VARCHAR2(500),
  DESCRIPTION      VARCHAR2(1000),
  ERROR_REASON     VARCHAR2(4000),
  ERROR_CONTENT    VARCHAR2(4000),
  ERROR_LEVEL      VARCHAR2(50),
  BIG_ERROR_FLAG   VARCHAR2(50),
  ALTER_FLAG       VARCHAR2(50),
  REALTER_FLAG     VARCHAR2(50),
  QUESTION_TYPE    VARCHAR2(50),
  QUESTION_ITEM    VARCHAR2(200),
  ERROR_TYPE       VARCHAR2(200),
  REMARK           VARCHAR2(1000),
  STATUS           VARCHAR2(50),
  DEL_FLAG         VARCHAR2(50),
  CREATOR_ID       VARCHAR2(50),
  CREATOR          VARCHAR2(200),
  CREATE_TIME      DATE,
  UPDATER_ID       VARCHAR2(50),
  UPDATER          VARCHAR2(200),
  UPDATE_TIME      DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.ROAD_QUESTION
  is '道路品质周报问题表';
comment on column IMS.ROAD_QUESTION.ID
  is '主键ID';
comment on column IMS.ROAD_QUESTION.VERSION
  is '作业版本';
comment on column IMS.ROAD_QUESTION.PARENT_TASK_CODE
  is '父任务编号';
comment on column IMS.ROAD_QUESTION.PARENT_TASK_NAME
  is '父任务';
comment on column IMS.ROAD_QUESTION.TASK_CODE
  is '子任务编号';
comment on column IMS.ROAD_QUESTION.TASK_NAME
  is '子任务';
comment on column IMS.ROAD_QUESTION.SECTION
  is '作业组';
comment on column IMS.ROAD_QUESTION.WORKER_ID
  is '作业员ID';
comment on column IMS.ROAD_QUESTION.WORKER
  is '作业员';
comment on column IMS.ROAD_QUESTION.WORK_DATE
  is '作业日期';
comment on column IMS.ROAD_QUESTION.AUDITOR_ID
  is '质检员ID';
comment on column IMS.ROAD_QUESTION.AUDITOR
  is '质检员';
comment on column IMS.ROAD_QUESTION.AUDIT_DATE
  is '质检日期';
comment on column IMS.ROAD_QUESTION.MAP_CODE
  is '图符号';
comment on column IMS.ROAD_QUESTION.FEATURE
  is '要素';
comment on column IMS.ROAD_QUESTION.DESCRIPTION
  is '问题说明';
comment on column IMS.ROAD_QUESTION.ERROR_REASON
  is '错误原因';
comment on column IMS.ROAD_QUESTION.ERROR_CONTENT
  is '错误内容';
comment on column IMS.ROAD_QUESTION.ERROR_LEVEL
  is '错误等级';
comment on column IMS.ROAD_QUESTION.BIG_ERROR_FLAG
  is '是否倾向性';
comment on column IMS.ROAD_QUESTION.ALTER_FLAG
  is '是否修改';
comment on column IMS.ROAD_QUESTION.REALTER_FLAG
  is '是否二次修改';
comment on column IMS.ROAD_QUESTION.QUESTION_TYPE
  is '问题类型';
comment on column IMS.ROAD_QUESTION.QUESTION_ITEM
  is '问题项目';
comment on column IMS.ROAD_QUESTION.ERROR_TYPE
  is '错误类型';
comment on column IMS.ROAD_QUESTION.REMARK
  is '备注';
comment on column IMS.ROAD_QUESTION.STATUS
  is '状态';
comment on column IMS.ROAD_QUESTION.DEL_FLAG
  is '删除标识';
comment on column IMS.ROAD_QUESTION.CREATOR_ID
  is '创建人ID';
comment on column IMS.ROAD_QUESTION.CREATOR
  is '创建人';
comment on column IMS.ROAD_QUESTION.CREATE_TIME
  is '创建时间';
comment on column IMS.ROAD_QUESTION.UPDATER_ID
  is '更新人ID';
comment on column IMS.ROAD_QUESTION.UPDATER
  is '更新人';
comment on column IMS.ROAD_QUESTION.UPDATE_TIME
  is '更新时间';
alter table IMS.ROAD_QUESTION
  add constraint PK_ROAD_QUESTION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROAD_REPORT
prompt ==========================
prompt
create table IMS.ROAD_REPORT
(
  ID               VARCHAR2(50) not null,
  VERSION          VARCHAR2(50),
  SECTION          VARCHAR2(200),
  WORKER_ID        VARCHAR2(50),
  WORKER           VARCHAR2(200),
  WORK_DATE        DATE,
  PARENT_TASK_CODE VARCHAR2(50),
  PARENT_TASK_NAME VARCHAR2(200),
  TASK_CODE        VARCHAR2(50),
  TASK_NAME        VARCHAR2(200),
  AUDITOR_ID       VARCHAR2(50),
  AUDITOR          VARCHAR2(200),
  AUDIT_TIME       DATE,
  WORK_NUM         NUMBER,
  AUDIT_NUM        NUMBER,
  ERROR_NUM        NUMBER,
  QUESTION_NUM     NUMBER,
  BIG_ERROR_NUM    NUMBER,
  PASS_RATE        NUMBER,
  EVALUATE         VARCHAR2(50),
  WEEK             VARCHAR2(50),
  REMARK           VARCHAR2(1000),
  STATUS           VARCHAR2(50),
  DEL_FLAG         VARCHAR2(50),
  CREATOR_ID       VARCHAR2(50),
  CREATOR          VARCHAR2(200),
  CREATE_TIME      DATE,
  UPDATER_ID       VARCHAR2(50),
  UPDATER          VARCHAR2(200),
  UPDATE_TIME      DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.ROAD_REPORT
  is '道路品质周报';
comment on column IMS.ROAD_REPORT.ID
  is '主键ID';
comment on column IMS.ROAD_REPORT.VERSION
  is '作业版本';
comment on column IMS.ROAD_REPORT.SECTION
  is '作业组';
comment on column IMS.ROAD_REPORT.WORKER_ID
  is '作业员编号';
comment on column IMS.ROAD_REPORT.WORKER
  is '作业员';
comment on column IMS.ROAD_REPORT.WORK_DATE
  is '作业时间';
comment on column IMS.ROAD_REPORT.PARENT_TASK_CODE
  is '父任务编号';
comment on column IMS.ROAD_REPORT.PARENT_TASK_NAME
  is '父任务';
comment on column IMS.ROAD_REPORT.TASK_CODE
  is '子任务编号';
comment on column IMS.ROAD_REPORT.TASK_NAME
  is '子任务';
comment on column IMS.ROAD_REPORT.AUDITOR_ID
  is '质检员编号';
comment on column IMS.ROAD_REPORT.AUDITOR
  is '质检员';
comment on column IMS.ROAD_REPORT.AUDIT_TIME
  is '质检日期';
comment on column IMS.ROAD_REPORT.WORK_NUM
  is '作业总量';
comment on column IMS.ROAD_REPORT.AUDIT_NUM
  is '质检量';
comment on column IMS.ROAD_REPORT.ERROR_NUM
  is '错误量';
comment on column IMS.ROAD_REPORT.QUESTION_NUM
  is '疑问量';
comment on column IMS.ROAD_REPORT.BIG_ERROR_NUM
  is '倾向性问题';
comment on column IMS.ROAD_REPORT.PASS_RATE
  is '合格率';
comment on column IMS.ROAD_REPORT.EVALUATE
  is '质检评定';
comment on column IMS.ROAD_REPORT.WEEK
  is '周次';
comment on column IMS.ROAD_REPORT.REMARK
  is '备注';
comment on column IMS.ROAD_REPORT.STATUS
  is '状态';
comment on column IMS.ROAD_REPORT.DEL_FLAG
  is '删除标识';
comment on column IMS.ROAD_REPORT.CREATOR_ID
  is '创建人ID';
comment on column IMS.ROAD_REPORT.CREATOR
  is '创建人';
comment on column IMS.ROAD_REPORT.CREATE_TIME
  is '创建时间';
comment on column IMS.ROAD_REPORT.UPDATER_ID
  is '更新人ID';
comment on column IMS.ROAD_REPORT.UPDATER
  is '更新人';
comment on column IMS.ROAD_REPORT.UPDATE_TIME
  is '更新时间';
alter table IMS.ROAD_REPORT
  add constraint PK_ROAD_REPORT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROLE_MODULE_PERMISSION_REL
prompt =========================================
prompt
create table IMS.ROLE_MODULE_PERMISSION_REL
(
  REL_ID               VARCHAR2(50) not null,
  ROLE_ID              VARCHAR2(50),
  MODULE_PERMISSION_ID VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.ROLE_MODULE_PERMISSION_REL
  is '角色模块权限表';
comment on column IMS.ROLE_MODULE_PERMISSION_REL.REL_ID
  is '关联表ID';
comment on column IMS.ROLE_MODULE_PERMISSION_REL.ROLE_ID
  is '角色ID';
comment on column IMS.ROLE_MODULE_PERMISSION_REL.MODULE_PERMISSION_ID
  is '模块权限ID';
alter table IMS.ROLE_MODULE_PERMISSION_REL
  add constraint PK_ROLE_MODULE_PERMISSION_REL primary key (REL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_DICTIONARY
prompt =============================
prompt
create table IMS.SYS_DICTIONARY
(
  UUID       VARCHAR2(50) not null,
  DICT_CODE  VARCHAR2(50),
  DICT_NAME  VARCHAR2(200),
  DICT_GROUP VARCHAR2(200),
  DICT_TYPE  VARCHAR2(200),
  SORT_INDEX VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.SYS_DICTIONARY
  is '用于存放写字典数据(根据不同类型进行划分，同一类型中也可以分为不同组)';
comment on column IMS.SYS_DICTIONARY.UUID
  is '主键';
comment on column IMS.SYS_DICTIONARY.DICT_CODE
  is '字典编码';
comment on column IMS.SYS_DICTIONARY.DICT_NAME
  is '字典内容';
comment on column IMS.SYS_DICTIONARY.DICT_GROUP
  is '字典组';
comment on column IMS.SYS_DICTIONARY.DICT_TYPE
  is '字典类型';
comment on column IMS.SYS_DICTIONARY.SORT_INDEX
  is '排序';
alter table IMS.SYS_DICTIONARY
  add constraint PK_SYS_DICTIONARY primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_GROUP
prompt ========================
prompt
create table IMS.SYS_GROUP
(
  GROUP_CODE        VARCHAR2(50) not null,
  GROUP_NAME        VARCHAR2(200),
  GROUP_TYPE        VARCHAR2(200),
  PARENT_GROUP_CODE VARCHAR2(50),
  OWNER             VARCHAR2(200),
  SORT_INDEX        VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.SYS_GROUP
  is '系统组织表';
comment on column IMS.SYS_GROUP.GROUP_CODE
  is '组编码';
comment on column IMS.SYS_GROUP.GROUP_NAME
  is '组名称';
comment on column IMS.SYS_GROUP.GROUP_TYPE
  is '组类别';
comment on column IMS.SYS_GROUP.PARENT_GROUP_CODE
  is '父组编码';
comment on column IMS.SYS_GROUP.OWNER
  is '所属对象';
comment on column IMS.SYS_GROUP.SORT_INDEX
  is '排序';
alter table IMS.SYS_GROUP
  add constraint PK_SYS_GROUP primary key (GROUP_CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_LOG
prompt ======================
prompt
create table IMS.SYS_LOG
(
  UUID        VARCHAR2(50) not null,
  USERNAME    VARCHAR2(200),
  OPT_CONTENT VARCHAR2(2000),
  CLIENT_IP   VARCHAR2(50),
  CREATE_TIME DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.SYS_LOG
  is '系统日志';
comment on column IMS.SYS_LOG.UUID
  is '主键';
comment on column IMS.SYS_LOG.USERNAME
  is '登录用户名';
comment on column IMS.SYS_LOG.OPT_CONTENT
  is '操作内容';
comment on column IMS.SYS_LOG.CLIENT_IP
  is '用户IP';
comment on column IMS.SYS_LOG.CREATE_TIME
  is '生成时间';
alter table IMS.SYS_LOG
  add constraint PK_SYS_LOG primary key (UUID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_MODULE
prompt =========================
prompt
create table IMS.SYS_MODULE
(
  MODULE_ID     VARCHAR2(50) not null,
  SYSTEM_ID     VARCHAR2(50),
  MODULE_CODE   VARCHAR2(50),
  MODULE_NAME   VARCHAR2(200),
  SORT_INDEX    VARCHAR2(50),
  MODULE_REMARK VARCHAR2(1000),
  MODULE_PID    VARCHAR2(50),
  MODULE_URL    VARCHAR2(200),
  URL_TARGET    VARCHAR2(50),
  ICON_CLASS    VARCHAR2(50),
  IS_SHOW       VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.SYS_MODULE
  is '系统模块表';
comment on column IMS.SYS_MODULE.MODULE_ID
  is '模块ID';
comment on column IMS.SYS_MODULE.SYSTEM_ID
  is '所属系统ID';
comment on column IMS.SYS_MODULE.MODULE_CODE
  is '模块编码';
comment on column IMS.SYS_MODULE.MODULE_NAME
  is '模块名称';
comment on column IMS.SYS_MODULE.SORT_INDEX
  is '排序';
comment on column IMS.SYS_MODULE.MODULE_REMARK
  is '模块备注';
comment on column IMS.SYS_MODULE.MODULE_PID
  is '父模块ID';
comment on column IMS.SYS_MODULE.MODULE_URL
  is '模块URL';
comment on column IMS.SYS_MODULE.URL_TARGET
  is 'URL跳转目标';
comment on column IMS.SYS_MODULE.ICON_CLASS
  is '模块图标类';
comment on column IMS.SYS_MODULE.IS_SHOW
  is '是否显示';
alter table IMS.SYS_MODULE
  add constraint PK_SYS_MODULE primary key (MODULE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_PERMISSION
prompt =============================
prompt
create table IMS.SYS_PERMISSION
(
  PERMISSION_ID   VARCHAR2(50) not null,
  PARENT_ID       VARCHAR2(50),
  PERMISSION_NAME VARCHAR2(200),
  DESCRIPTION     VARCHAR2(500),
  MODULE_ID       VARCHAR2(50),
  SORT_INDEX      VARCHAR2(10),
  STATUS          VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table IMS.SYS_PERMISSION
  add constraint PK_SYS_PERMISSION primary key (PERMISSION_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table IMS.SYS_ROLE
(
  ROLE_ID     VARCHAR2(50) not null,
  PARENT_ID   VARCHAR2(50),
  ROLE_NAME   VARCHAR2(200),
  DESCRIPTION VARCHAR2(500),
  SORT_INDEX  VARCHAR2(10),
  STATUS      VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column IMS.SYS_ROLE.STATUS
  is '通过控制角色来控制用户行为';
alter table IMS.SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_USER
prompt =======================
prompt
create table IMS.SYS_USER
(
  ID          VARCHAR2(50) not null,
  PASSWORD    VARCHAR2(200),
  GROUP_ID    VARCHAR2(50),
  NICKNAME    VARCHAR2(200),
  TEL         VARCHAR2(20),
  EMAIL       VARCHAR2(50),
  STATUS      CHAR(1),
  CREATE_TIME DATE,
  ATTR1       VARCHAR2(200),
  ATTR2       VARCHAR2(200),
  ATTR3       VARCHAR2(200),
  ATTR4       VARCHAR2(200),
  ATTR5       VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.SYS_USER
  is '用户表';
comment on column IMS.SYS_USER.ID
  is '和员工编号保持一致';
comment on column IMS.SYS_USER.PASSWORD
  is '密码';
comment on column IMS.SYS_USER.GROUP_ID
  is '用户组ID';
comment on column IMS.SYS_USER.NICKNAME
  is '与员工名称保持一致';
comment on column IMS.SYS_USER.STATUS
  is '0为不可用，1为可用';
alter table IMS.SYS_USER
  add constraint PK_SYS_USER primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TEST_GRADE
prompt =========================
prompt
create table IMS.TEST_GRADE
(
  ID             VARCHAR2(50) not null,
  VERSION        VARCHAR2(50),
  EMP_ID         VARCHAR2(50),
  EMP_NAME       VARCHAR2(200),
  SECTION        VARCHAR2(200),
  PAPER_GRADE    NUMBER,
  COM_GRADE_ROAD NUMBER,
  COM_GRADE_POI  NUMBER,
  TOTAL_GRADE    NUMBER,
  TEST_DATE      DATE,
  CREATE_TIME    DATE,
  CREATOR_ID     VARCHAR2(50),
  CREATOR        VARCHAR2(200),
  UPDATE_TIME    DATE,
  UPDATER_ID     VARCHAR2(50),
  UPDATER        VARCHAR2(200),
  STATUS         VARCHAR2(10),
  REMARK         VARCHAR2(200),
  DEL_FLAG       VARCHAR2(20),
  ATTR1          VARCHAR2(200),
  ATTR2          VARCHAR2(200),
  ATTR3          VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.TEST_GRADE
  is '全要素考核成绩表';
comment on column IMS.TEST_GRADE.ID
  is '主键';
comment on column IMS.TEST_GRADE.VERSION
  is '版本号';
comment on column IMS.TEST_GRADE.EMP_ID
  is '员工编号';
comment on column IMS.TEST_GRADE.EMP_NAME
  is '员工姓名';
comment on column IMS.TEST_GRADE.SECTION
  is '所属科室';
comment on column IMS.TEST_GRADE.PAPER_GRADE
  is '笔试成绩';
comment on column IMS.TEST_GRADE.COM_GRADE_ROAD
  is '机试成绩(道路)';
comment on column IMS.TEST_GRADE.COM_GRADE_POI
  is '机试成绩(设施)';
comment on column IMS.TEST_GRADE.TOTAL_GRADE
  is '综合成绩';
comment on column IMS.TEST_GRADE.TEST_DATE
  is '考核时间';
comment on column IMS.TEST_GRADE.CREATE_TIME
  is '创建时间';
comment on column IMS.TEST_GRADE.CREATOR_ID
  is '创建人ID';
comment on column IMS.TEST_GRADE.CREATOR
  is '创建人';
comment on column IMS.TEST_GRADE.UPDATE_TIME
  is '更新时间';
comment on column IMS.TEST_GRADE.UPDATER_ID
  is '更新人ID';
comment on column IMS.TEST_GRADE.UPDATER
  is '更新人';
comment on column IMS.TEST_GRADE.STATUS
  is '状态';
comment on column IMS.TEST_GRADE.REMARK
  is '备注';
comment on column IMS.TEST_GRADE.DEL_FLAG
  is '删除标识';
comment on column IMS.TEST_GRADE.ATTR1
  is 'attr1';
comment on column IMS.TEST_GRADE.ATTR2
  is 'attr2';
comment on column IMS.TEST_GRADE.ATTR3
  is 'attr3';
alter table IMS.TEST_GRADE
  add constraint PK_TEST_GRADE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TEST_IMPORT
prompt ==========================
prompt
create table IMS.TEST_IMPORT
(
  NAME        VARCHAR2(200),
  SEX         VARCHAR2(200),
  AGE         VARCHAR2(20),
  CODE        VARCHAR2(20),
  ADDRESS     VARCHAR2(200),
  CREATE_TIME DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table USER_ROLE_REL
prompt ============================
prompt
create table IMS.USER_ROLE_REL
(
  REL_ID   VARCHAR2(50) not null,
  USERNAME VARCHAR2(50),
  ROLE_ID  VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table IMS.USER_ROLE_REL
  add constraint PK_USER_ROLE_REL primary key (REL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WORK_DATA
prompt ========================
prompt
create table IMS.WORK_DATA
(
  WORK_DATA_ID VARCHAR2(50) not null,
  VERSION      VARCHAR2(50),
  EMP_ID       VARCHAR2(50),
  EMP_NAME     VARCHAR2(100),
  WORK_TYPE    VARCHAR2(50),
  QUANTITY     NUMBER,
  QUALITY      NUMBER,
  Q_ERROR      NUMBER,
  Q_RATE       NUMBER,
  MONITOR      NUMBER,
  M_ERROR      NUMBER,
  M_RATE       NUMBER,
  MAJOR_ERROR  NUMBER,
  EFFICIENCY   NUMBER,
  Q_EFFICIENCY NUMBER,
  CREATE_TIME  DATE,
  CREATOR_ID   VARCHAR2(50),
  CREATOR      VARCHAR2(200),
  UPDATE_TIME  DATE,
  UPDATER_ID   VARCHAR2(50),
  UPDATER      VARCHAR2(200),
  STATUS       VARCHAR2(10),
  ATTR1        VARCHAR2(200),
  ATTR2        VARCHAR2(200),
  ATTR3        VARCHAR2(200),
  ATTR4        VARCHAR2(200),
  ATTR5        VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column IMS.WORK_DATA.WORK_DATA_ID
  is '主键';
comment on column IMS.WORK_DATA.VERSION
  is '版本号';
comment on column IMS.WORK_DATA.EMP_ID
  is '员工编号';
comment on column IMS.WORK_DATA.EMP_NAME
  is '员工姓名';
comment on column IMS.WORK_DATA.QUANTITY
  is '作业量';
comment on column IMS.WORK_DATA.QUALITY
  is '质检量';
comment on column IMS.WORK_DATA.Q_ERROR
  is '质检错误量';
comment on column IMS.WORK_DATA.Q_RATE
  is '质检品质率';
comment on column IMS.WORK_DATA.MONITOR
  is '监察量';
comment on column IMS.WORK_DATA.M_ERROR
  is '监察错误量';
comment on column IMS.WORK_DATA.M_RATE
  is '监察品质率';
comment on column IMS.WORK_DATA.MAJOR_ERROR
  is '重大品质事故';
comment on column IMS.WORK_DATA.EFFICIENCY
  is '作业功效';
comment on column IMS.WORK_DATA.Q_EFFICIENCY
  is '质检功效';
comment on column IMS.WORK_DATA.CREATE_TIME
  is '创建时间';
comment on column IMS.WORK_DATA.CREATOR_ID
  is '创建人ID';
comment on column IMS.WORK_DATA.CREATOR
  is '创建人';
comment on column IMS.WORK_DATA.UPDATE_TIME
  is '更新时间';
comment on column IMS.WORK_DATA.UPDATER_ID
  is '更新人ID';
comment on column IMS.WORK_DATA.UPDATER
  is '更新人';
comment on column IMS.WORK_DATA.STATUS
  is '逻辑删除标识';
alter table IMS.WORK_DATA
  add constraint PK_WORK_DATA primary key (WORK_DATA_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WORK_DIARY
prompt =========================
prompt
create table IMS.WORK_DIARY
(
  ID              VARCHAR2(50) not null,
  EMP_ID          VARCHAR2(50),
  EMP_NAME        VARCHAR2(200),
  SECTION         VARCHAR2(50),
  WORK_DATE       DATE,
  WORK_TYPE       VARCHAR2(200),
  WORK_MODULE     VARCHAR2(200),
  WORK_TIME_START VARCHAR2(50),
  WORK_TIME_END   VARCHAR2(50),
  WORK_HOURS      VARCHAR2(50),
  WORK_CONTENT    VARCHAR2(4000),
  REMARK          VARCHAR2(4000),
  STATUS          VARCHAR2(50),
  DEL_FLAG        VARCHAR2(50),
  CREATOR_ID      VARCHAR2(50),
  CREATOR         VARCHAR2(200),
  CREATE_TIME     DATE,
  UPDATER_ID      VARCHAR2(50),
  UPDATER         VARCHAR2(200),
  UPDATE_TIME     DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table IMS.WORK_DIARY
  is '工作日志表';
comment on column IMS.WORK_DIARY.ID
  is '主键ID';
comment on column IMS.WORK_DIARY.EMP_ID
  is '员??编号';
comment on column IMS.WORK_DIARY.EMP_NAME
  is '员工姓名';
comment on column IMS.WORK_DIARY.SECTION
  is '科室';
comment on column IMS.WORK_DIARY.WORK_DATE
  is '工作日期';
comment on column IMS.WORK_DIARY.WORK_TYPE
  is '工作类型';
comment on column IMS.WORK_DIARY.WORK_MODULE
  is '工作模块';
comment on column IMS.WORK_DIARY.WORK_TIME_START
  is '工作开始时间';
comment on column IMS.WORK_DIARY.WORK_TIME_END
  is '工作结束时间';
comment on column IMS.WORK_DIARY.WORK_HOURS
  is '工作时长';
comment on column IMS.WORK_DIARY.WORK_CONTENT
  is '工作内容';
comment on column IMS.WORK_DIARY.REMARK
  is '备注';
comment on column IMS.WORK_DIARY.STATUS
  is '状态';
comment on column IMS.WORK_DIARY.DEL_FLAG
  is '删除标识';
comment on column IMS.WORK_DIARY.CREATOR_ID
  is '创建人ID';
comment on column IMS.WORK_DIARY.CREATOR
  is '创建人';
comment on column IMS.WORK_DIARY.CREATE_TIME
  is '创建时间';
comment on column IMS.WORK_DIARY.UPDATER_ID
  is '更新人ID';
comment on column IMS.WORK_DIARY.UPDATER
  is '更新人';
comment on column IMS.WORK_DIARY.UPDATE_TIME
  is '更新时间';
alter table IMS.WORK_DIARY
  add constraint PK_WORK_DIARY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating view KPI_REPORT
prompt ========================
prompt
create or replace view ims.kpi_report as
select "WORK_TYPE","AUDIT_NUM","ERROR_NUM","YEAR","MONTH" from (
select '道路' as work_type,
       sum(audit_num) as audit_num,
       sum(error_num) as error_num,
       to_char(audit_time, 'yyyy') as year,
       to_char(audit_time, 'mm') as month
  from road_report t
 group by to_char(audit_time, 'yyyy'), to_char(audit_time, 'mm')
 --order by to_char(audit_time, 'yyyy'), to_char(audit_time, 'IW')
union all
select '设施' as work_type,
       sum(cp_audit_num) + sum(ca_audit_num) + sum(ep_audit_num) +
       sum(ea_audit_num) as audit_num,
       sum(cp_error_num)+sum(ca_error_num)+sum(ep_error_num)+
       sum(ea_error_num) as error_num,
       to_char(stat_date,'yyyy') as year,
       to_char(stat_date,'mm') as month
  from poi_report t
  group by to_char(stat_date,'yyyy'),to_char(stat_date,'mm')
  --order by to_char(stat_date,'yyyy'),to_char(stat_date,'IW')
) a order by a.work_type,a.year,a.month;

prompt
prompt Creating view QUESTION_DETAIL_REPORT
prompt ====================================
prompt
create or replace view ims.question_detail_report as
select "WORK_TYPE","YEAR","ERROR_TYPE","ERROR_NUM","WORKER_ID","WORKER","SECTION" from (
  select '道路' as work_type,to_char(t.audit_date, 'yyyy') as year,
         t.error_type,
         count(t.error_type) as error_num,t.worker_id,t.worker,t.section
    from road_question t
   group by to_char(t.audit_date, 'yyyy'), t.error_type,t.worker_id,t.worker,t.section
   having count(t.error_type)>0
   --order by year
   union all
   select '设施' as work_type ,to_char(t.stat_date, 'yyyy') as year,
          t.error_type,
          count(t.error_type) as error_num,t.worker_id,t.worker,t.section
     from poi_question t
    group by to_char(t.stat_date, 'yyyy'), t.error_type ,t.worker_id,t.worker,t.section
    having count(t.error_type)>0
    --order by year
) order by work_type,year;

prompt
prompt Creating view QUESTION_REPORT
prompt =============================
prompt
create or replace view ims.question_report as
select "WORK_TYPE","YEAR","ERROR_TYPE","ERROR_NUM" from (
  select '道路' as work_type,to_char(audit_date, 'yyyy') as year,
         error_type,
         count(error_type) as error_num
    from road_question
   group by to_char(audit_date, 'yyyy'), error_type
   having count(error_type)>0
   --order by year
   union all
   select '设施' as work_type ,to_char(stat_date, 'yyyy') as year,
          error_type,
          count(error_type) as error_num
     from poi_question
    group by to_char(stat_date, 'yyyy'), error_type
    having count(error_type)>0
    --order by year
) order by work_type,year;

prompt
prompt Creating view ROAD_QUALITY
prompt ==========================
prompt
CREATE OR REPLACE VIEW IMS.ROAD_QUALITY AS
SELECT t.version,t.worker_id,t.worker,t.section,'道路图标' as work_type,
        SUM (t.work_num) as work_total_num ,
        SUM (t.audit_num) as audit_total_num ,
        SUM (CASE WHEN m.question_type='错误' THEN 1 ELSE 0 END ) as error_total_num,
        SUM (CASE WHEN m.question_type='疑问' THEN 1 ELSE 0 END ) as question_num,
        SUM (CASE WHEN m.big_error_flag='是' THEN 1 ELSE 0 END ) AS big_error_num
        FROM road_report t ,road_question m
        WHERE t.task_name=m.task_name(+)
        group by t.version,t.worker_id,t.worker,t.section;

prompt
prompt Creating view WEEK_DETAIL_REPORT
prompt ================================
prompt
create or replace view ims.week_detail_report as
select "YEAR","WEEK","WORKER_ID","WORKER","SECTION","WORK_TYPE","AUDIT_NUM","ERROR_NUM" from (
select to_char(audit_time, 'yyyy') as year,
       to_char(audit_time, 'IW') as week,
       t.worker_id,
       t.worker,
       t.section,
       '道路' as work_type,
       sum(audit_num) as audit_num,
       sum(error_num) as error_num
  from road_report t
 group by to_char(audit_time, 'yyyy'), to_char(audit_time, 'IW'),t.worker_id,t.worker,t.section
 --order by to_char(audit_time, 'yyyy'), to_char(audit_time, 'IW')
union all
select to_char(stat_date,'yyyy') as year,
       to_char(stat_date,'IW') as week,
       t.worker_id,
       t.worker,
       t.section,
       '设施' as work_type,
       sum(cp_audit_num) + sum(ca_audit_num) + sum(ep_audit_num) +
       sum(ea_audit_num) as audit_num,
       sum(cp_error_num)+sum(ca_error_num)+sum(ep_error_num)+
       sum(ea_error_num) as error_num
  from poi_report t
  group by to_char(stat_date,'yyyy'),to_char(stat_date,'IW'),t.worker_id,t.worker,t.section
  --order by to_char(stat_date,'yyyy'),to_char(stat_date,'IW')
) a order by a.work_type,a.year,a.week;

prompt
prompt Creating view WEEK_REPORT
prompt =========================
prompt
create or replace view ims.week_report as
select "WORK_TYPE","AUDIT_NUM","ERROR_NUM","YEAR","WEEK" from (
select '道路' as work_type,
       sum(audit_num) as audit_num,
       sum(error_num) as error_num,
       to_char(audit_time, 'yyyy') as year,
       to_char(audit_time, 'IW') as week
  from road_report t
 group by to_char(audit_time, 'yyyy'), to_char(audit_time, 'IW')
 --order by to_char(audit_time, 'yyyy'), to_char(audit_time, 'IW')
union all
select '设施' as work_type,
       sum(cp_audit_num) + sum(ca_audit_num) + sum(ep_audit_num) +
       sum(ea_audit_num) as audit_num,
       sum(cp_error_num)+sum(ca_error_num)+sum(ep_error_num)+
       sum(ea_error_num) as error_num,
       to_char(stat_date,'yyyy') as year,
       to_char(stat_date,'IW') as week
  from poi_report t
  group by to_char(stat_date,'yyyy'),to_char(stat_date,'IW')
  --order by to_char(stat_date,'yyyy'),to_char(stat_date,'IW')
) a order by a.work_type,a.year,a.week;

prompt
prompt Creating view WORK_EFFICIENCY
prompt =============================
prompt
create or replace view ims.work_efficiency as
select emp_id,emp_name,work_date,
sum(case when task_type='道路前期录入' then work_load else 0 end) as before_road_input_num,
sum(case when task_type='道路前期录入' then work_hours else 0 end) as before_road_input_hours,
sum(case when task_type='道路前期质检' then work_load else 0 end) as before_road_audit_num,
sum(case when task_type='道路前期质检' then work_hours else 0 end) as before_road_audit_hours,
sum(case when task_type='FM平台录入' and task_name='名称'||chr(38)||'拼音作业' then work_load else 0 end) as fm_cp_input_num,
sum(case when task_type='FM平台录入' and task_name='名称'||chr(38)||'拼音作业' then work_hours else 0 end) as fm_cp_input_hours,
sum(case when task_type='FM平台质检' and task_name='名称'||chr(38)||'拼音作业' then work_load else 0 end ) as fm_cp_audit_num,
sum(case when task_type='FM平台录入' and task_name='名称'||chr(38)||'拼音作业' then work_hours else 0 end) as fm_cp_audit_hours,
sum(case when task_type='FM平台录入' and task_name='中文地址拆分作业' then work_load else 0 end) as fm_ca_input_num,
sum(case when task_type='FM平台录入' and task_name='中文地址拆分作业' then work_hours else 0 end) as fm_ca_input_hours,
sum(case when task_type='FM平台质检' and task_name='中文地址拆分作业' then work_load else 0 end) as fm_ca_audit_num,
sum(case when task_type='FM平台质检' and task_name='中文地址拆分作业' then work_hours else 0 end) as fm_ca_audit_hours,
sum(case when task_type='FM平台录入' and task_name='人工确认英文作业' then work_load else 0 end) as fm_ep_input_num,
sum(case when task_type='FM平台录入' and task_name='人工确认英文作业' then work_hours else 0 end) as fm_ep_input_hours,
sum(case when task_type='FM平台质检' and task_name='人工确认英文作业' then work_load else 0 end) as fm_ep_audit_num,
sum(case when task_type='FM平台质检' and task_name='人工确认英文作业' then work_hours else 0 end) as fm_ep_audit_hours,
sum(case when task_type='FM平台录入' and task_name='英文地址作业' then work_load else 0 end) as fm_ea_input_num,
sum(case when task_type='FM平台录入' and task_name='英文地址作业' then work_hours else 0 end) as fm_ea_input_hours,
sum(case when task_type='FM平台质检' and task_name='英文地址作业' then work_load else 0 end) as fm_ea_audit_num,
sum(case when task_type='FM平台质检' and task_name='英文地址作业' then work_hours else 0 end) as fm_ea_audit_hours,
sum(case when task_type='深度信息作业'  then work_load else 0 end) as dp_info_work_num,
sum(case when task_type='深度信息作业'  then work_hours else 0 end) as dp_info_work_hours,
sum(case when task_type='深度信息质检'  then work_load else 0 end) as dp_info_audit_num,
sum(case when task_type='深度信息质检'  then work_hours else 0 end) as dp_info_audit_hours,
sum(case when task_type='代理店前期录入'  then work_load else 0 end) as before_agency_work_num,
sum(case when task_type='代理店前期录入'  then work_hours else 0 end) as before_agency_work_hours,
sum(case when task_type='代理店后期作业'  then work_load else 0 end) as after_agency_work_num,
sum(case when task_type='代理店后期作业'  then work_hours else 0 end) as after_agency_work_hours,
sum(case when task_type='道路前期录入' or
              task_type='道路前期质检' or
              (task_type='FM平台录入' and task_name='名称'||chr(38)||'拼音作业') or
              (task_type='FM平台质检' and task_name='名称'||chr(38)||'拼音作业') or
              (task_type='FM平台录入' and task_name='中文地址拆分作业') or
              (task_type='FM平台质检' and task_name='中文地址拆分作业') or
              (task_type='FM平台录入' and task_name='人工确认英文作业') or
              (task_type='FM平台质检' and task_name='人工确认英文作业') or
              (task_type='FM平台录入' and task_name='英文地址作业') or
              (task_type='FM平台质检' and task_name='英文地址作业') or
              task_type='深度信息作业' or
              task_type='深度信息质检' or
              task_type='代理店前期录入' or
              task_type='代理店后期作业'
then work_hours else 0 end) as hours
from diary_info group by emp_name,emp_id,work_date;


spool off
