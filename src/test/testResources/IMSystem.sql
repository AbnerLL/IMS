/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2017/11/5 22:03:05                           */
/*==============================================================*/


drop table EMP cascade constraints;

drop table ims.EMP_RESUME cascade constraints;

drop table ims.PRO_ABILITY cascade constraints;

drop table ims.ROLE_PERMISSION_REL cascade constraints;

drop table ims.SYS_PERMISSION cascade constraints;

drop table ims.WORK_DATA cascade constraints;

drop table ims.WORK_DIARY cascade constraints;

drop table ims.pre_grade cascade constraints;

drop table ims.sys_role cascade constraints;

drop table ims.sys_user cascade constraints;

drop table ims.test_grade cascade constraints;

drop table ims.user_role_rel cascade constraints;

drop user ims;

/*==============================================================*/
/* User: ims                                                    */
/*==============================================================*/
create user ims identified by '';

/*==============================================================*/
/* Table: EMP                                                   */
/*==============================================================*/
create table EMP  (
   emp_id               VARCHAR2(50)                    not null,
   emp_name             VARCHAR2(200),
   emp_sex              VARCHAR2(10),
   emp_hiredate         DATE,
   emp_entry_age        NUMBER(2),
   emp_dept             VARCHAR2(100),
   emp_sec              VARCHAR2(100),
   emp_post             VARCHAR2(100),
   emp_email            VARCHAR2(50),
   emp_tel              VARCHAR2(20),
   create_time          DATE,
   update_time          DATE,
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_EMP primary key (emp_id)
);

comment on column EMP.emp_id is
'员工编号';

comment on column EMP.emp_name is
'员工名称';

comment on column EMP.emp_sex is
'员工性别';

comment on column EMP.emp_hiredate is
'入职时间';

comment on column EMP.emp_entry_age is
'员工司龄';

comment on column EMP.emp_dept is
'所属部门';

comment on column EMP.emp_sec is
'所在科室';

comment on column EMP.emp_post is
'职务名称';

comment on column EMP.emp_email is
'员工邮箱';

comment on column EMP.emp_tel is
'联系方式';

comment on column EMP.create_time is
'创建时间';

comment on column EMP.update_time is
'更新时间';

/*==============================================================*/
/* Table: EMP_RESUME                                            */
/*==============================================================*/
create table ims.EMP_RESUME  (
   emp_id               VARCHAR2(50)                    not null,
   emp_name             VARCHAR2(200),
   speciality           VARCHAR2(4000),
   certificate          VARCHAR2(4000),
   award                VARCHAR2(4000),
   training             VARCHAR2(4000),
   project              VARCHAR2(4000),
   work_record          VARCHAR2(4000),
   create_time          DATE,
   constraint PK_EMP_RESUME primary key ()
);

comment on column ims.EMP_RESUME.emp_id is
'员工编号';

comment on column ims.EMP_RESUME.emp_name is
'员工姓名';

comment on column ims.EMP_RESUME.speciality is
'专长及特长';

comment on column ims.EMP_RESUME.certificate is
'认证及证书';

comment on column ims.EMP_RESUME.award is
'奖励情况';

comment on column ims.EMP_RESUME.training is
'培训情况';

comment on column ims.EMP_RESUME.project is
'项目经验';

comment on column ims.EMP_RESUME.work_record is
'工作履历';

comment on column ims.EMP_RESUME.create_time is
'创建时间';

/*==============================================================*/
/* Table: PRO_ABILITY                                           */
/*==============================================================*/
create table ims.PRO_ABILITY  (
   id                   VARCHAR2(50)                    not null,
   version              VARCHAR2(50),
   emp_id               VARCHAR2(50),
   emp_name             VARCHAR2(200),
   R_MARK_LOG           VARCHAR2(10),
   R_MARK_TEST          VARCHAR2(10),
   CH_POI_LOG           VARCHAR2(10),
   CH_POI_TEST          VARCHAR2(10),
   EN_POI_LOG           VARCHAR2(10),
   EN_POI_TEST          VARCHAR2(10),
   ROAD_ITEM_LOG        VARCHAR2(10),
   ROAD_ITEM_TEST       VARCHAR2(10),
   POI_ITEM_LOG         VARCHAR2(10),
   POI_ITEM_TEST        VARCHAR2(10),
   DEPTH_INFO_LOG       VARCHAR2(10),
   DEPTH_INFO_TEST      VARCHAR2(10),
   POINT_LOG            VARCHAR2(10),
   POINT_TEST           VARCHAR2(10),
   AGENCY_LOG           VARCHAR2(10),
   AGENCY_TEST          VARCHAR2(10),
   create_time          DATE,
   creator_id           VARCHAR2(50),
   creator              VARCHAR2(200),
   update_time          DATE,
   updater_id           VARCHAR2(50),
   updater              VARCHAR2(200),
   status               VARCHAR2(10),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_PRO_ABILITY primary key (id)
);

comment on column ims.PRO_ABILITY.id is
'主键';

comment on column ims.PRO_ABILITY.version is
'版本号';

comment on column ims.PRO_ABILITY.emp_id is
'员工编号';

comment on column ims.PRO_ABILITY.emp_name is
'员工姓名';

comment on column ims.PRO_ABILITY.R_MARK_LOG is
'道路前期录入能力';

comment on column ims.PRO_ABILITY.R_MARK_TEST is
'道路前期检验能力';

comment on column ims.PRO_ABILITY.CH_POI_LOG is
'设施—中文录入能力';

comment on column ims.PRO_ABILITY.CH_POI_TEST is
'设施—中文检验能力';

comment on column ims.PRO_ABILITY.EN_POI_LOG is
'设施—英文录入能力';

comment on column ims.PRO_ABILITY.EN_POI_TEST is
'设施—英文检验能力';

comment on column ims.PRO_ABILITY.ROAD_ITEM_LOG is
'道路后期录入能力';

comment on column ims.PRO_ABILITY.ROAD_ITEM_TEST is
'道路后期检验能力';

comment on column ims.PRO_ABILITY.POI_ITEM_LOG is
'设施后期录入能力';

comment on column ims.PRO_ABILITY.POI_ITEM_TEST is
'设施后期检验能力';

comment on column ims.PRO_ABILITY.DEPTH_INFO_LOG is
'深度信息录入能力';

comment on column ims.PRO_ABILITY.DEPTH_INFO_TEST is
'深度信息检验能力';

comment on column ims.PRO_ABILITY.POINT_LOG is
'点位录入能力';

comment on column ims.PRO_ABILITY.POINT_TEST is
'点位检验能力';

comment on column ims.PRO_ABILITY.AGENCY_LOG is
'代理店录入能力';

comment on column ims.PRO_ABILITY.AGENCY_TEST is
'代理店检验能力';

comment on column ims.PRO_ABILITY.create_time is
'创建时间';

comment on column ims.PRO_ABILITY.creator_id is
'创建人ID';

comment on column ims.PRO_ABILITY.creator is
'创建人';

comment on column ims.PRO_ABILITY.update_time is
'更新时间';

comment on column ims.PRO_ABILITY.updater_id is
'更新人ID';

comment on column ims.PRO_ABILITY.updater is
'更新人';

comment on column ims.PRO_ABILITY.status is
'逻辑删除标识';

/*==============================================================*/
/* Table: ROLE_PERMISSION_REL                                   */
/*==============================================================*/
create table ims.ROLE_PERMISSION_REL  (
   rel_id               VARCHAR2(50)                    not null,
   role_id              VARCHAR2(50),
   PERMISSION_ID        VARCHAR2(50),
   constraint PK_ROLE_PERMISSION_REL primary key (rel_id)
);

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table ims.SYS_PERMISSION  (
   PERMISSION_ID        VARCHAR2(50)                    not null,
   PARENT_ID            VARCHAR2(50),
   PERMISSION_NAME      VARCHAR2(200),
   DESCRIPTION          VARCHAR2(500),
   module_id            VARCHAR2(50),
   sort_index           VARCHAR2(10),
   status               VARCHAR2(10),
   constraint PK_SYS_PERMISSION primary key (PERMISSION_ID)
);

/*==============================================================*/
/* Table: WORK_DATA                                             */
/*==============================================================*/
create table ims.WORK_DATA  (
   work_data_id         VARCHAR2(50)                    not null,
   version              VARCHAR2(50),
   emp_id               VARCHAR2(50),
   emp_name             VARCHAR2(100),
   work_type            VARCHAR2(50),
   QUANTITY             NUMBER,
   QUALITY              NUMBER,
   Q_ERROR              NUMBER,
   Q_RATE               NUMBER,
   MONITOR              NUMBER,
   M_ERROR              NUMBER,
   M_RATE               NUMBER,
   MAJOR_ERROR          NUMBER,
   EFFICIENCY           NUMBER,
   Q_EFFICIENCY         NUMBER,
   create_time          DATE,
   creator_id           VARCHAR2(50),
   creator              VARCHAR2(200),
   update_time          DATE,
   updater_id           VARCHAR2(50),
   updater              VARCHAR2(200),
   status               VARCHAR2(10),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_WORK_DATA primary key (work_data_id)
);

comment on column ims.WORK_DATA.work_data_id is
'主键';

comment on column ims.WORK_DATA.version is
'版本号';

comment on column ims.WORK_DATA.emp_id is
'员工编号';

comment on column ims.WORK_DATA.emp_name is
'员工姓名';

comment on column ims.WORK_DATA.QUANTITY is
'作业量';

comment on column ims.WORK_DATA.QUALITY is
'质检量';

comment on column ims.WORK_DATA.Q_ERROR is
'质检错误量';

comment on column ims.WORK_DATA.Q_RATE is
'质检品质率';

comment on column ims.WORK_DATA.MONITOR is
'监察量';

comment on column ims.WORK_DATA.M_ERROR is
'监察错误量';

comment on column ims.WORK_DATA.M_RATE is
'监察品质率';

comment on column ims.WORK_DATA.MAJOR_ERROR is
'重大品质事故';

comment on column ims.WORK_DATA.EFFICIENCY is
'作业功效';

comment on column ims.WORK_DATA.Q_EFFICIENCY is
'质检功效';

comment on column ims.WORK_DATA.create_time is
'创建时间';

comment on column ims.WORK_DATA.creator_id is
'创建人ID';

comment on column ims.WORK_DATA.creator is
'创建人';

comment on column ims.WORK_DATA.update_time is
'更新时间';

comment on column ims.WORK_DATA.updater_id is
'更新人ID';

comment on column ims.WORK_DATA.updater is
'更新人';

comment on column ims.WORK_DATA.status is
'逻辑删除标识';

/*==============================================================*/
/* Table: WORK_DIARY                                            */
/*==============================================================*/
create table ims.WORK_DIARY  (
   ID                   VARCHAR2(50)                    not null,
   emp_id               VARCHAR2(50),
   emp_name             VARCHAR2(200),
   work_date            DATE,
   work_type            VARCHAR2(200),
   HOURS                VARCHAR2(50),
   CONTENT              VARCHAR2(4000),
   REMARKS              VARCHAR2(4000),
   status               VARCHAR2(10),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   constraint PK_WORK_DIARY primary key (ID)
);

/*==============================================================*/
/* Table: pre_grade                                             */
/*==============================================================*/
create table ims.pre_grade  (
   ID                   VARCHAR2(50)                    not null,
   version              VARCHAR2(50),
   emp_id               VARCHAR2(50),
   emp_name             VARCHAR2(200),
   grade                NUMBER,
   sec_grade            NUMBER,
   create_time          DATE,
   creator_id           VARCHAR2(50),
   creator              VARCHAR2(200),
   update_time          DATE,
   updater_id           VARCHAR2(50),
   updater              VARCHAR2(200),
   status               VARCHAR2(10),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_PRE_GRADE primary key (ID)
);

comment on column ims.pre_grade.ID is
'主键';

comment on column ims.pre_grade.version is
'版本号';

comment on column ims.pre_grade.emp_id is
'员工编号';

comment on column ims.pre_grade.emp_name is
'员工姓名';

comment on column ims.pre_grade.grade is
'考核成绩';

comment on column ims.pre_grade.sec_grade is
'补考成绩';

comment on column ims.pre_grade.create_time is
'创建时间';

comment on column ims.pre_grade.creator_id is
'创建人ID';

comment on column ims.pre_grade.creator is
'创建人';

comment on column ims.pre_grade.update_time is
'更新时间';

comment on column ims.pre_grade.updater_id is
'更新人ID';

comment on column ims.pre_grade.updater is
'更新人';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table ims.sys_role  (
   ROLE_ID              VARCHAR2(50)                    not null,
   PARENT_ID            VARCHAR2(50),
   ROLE_NAME            VARCHAR2(200),
   DESCRIPTION          VARCHAR2(500),
   sort_index           VARCHAR2(10),
   status               VARCHAR2(10),
   constraint PK_SYS_ROLE primary key (ROLE_ID)
);

comment on column ims.sys_role.status is
'通过控制角色来控制用户行为';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table ims.sys_user  (
   username             VARCHAR2(50)                    not null,
   password             VARCHAR2(200),
   group_id             VARCHAR2(50),
   nickname             VARCHAR2(200),
   tel                  VARCHAR2(20),
   email                VARCHAR2(50),
   status               CHAR(1),
   create_time          DATE,
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_SYS_USER primary key (username)
);

comment on table ims.sys_user is
'用户表';

comment on column ims.sys_user.username is
'和员工编号保持一致';

comment on column ims.sys_user.password is
'密码';

comment on column ims.sys_user.group_id is
'用户组ID';

comment on column ims.sys_user.nickname is
'与员工名称保持一致';

comment on column ims.sys_user.status is
'0为不可用，1为可用';

/*==============================================================*/
/* Table: test_grade                                            */
/*==============================================================*/
create table ims.test_grade  (
   ID                   VARCHAR2(50)                    not null,
   version              VARCHAR2(50),
   emp_id               VARCHAR2(50),
   emp_name             VARCHAR2(200),
   PAPER_GRADE          NUMBER,
   COM_GRADE_ROAD       NUMBER,
   COM_GRADE_POI        NUMBER,
   TOTAL_GRADE          NUMBER,
   create_time          DATE,
   creator_id           VARCHAR2(50),
   creator              VARCHAR2(200),
   update_time          DATE,
   updater_id           VARCHAR2(50),
   updater              VARCHAR2(200),
   status               VARCHAR2(10),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_TEST_GRADE primary key (ID)
);

comment on column ims.test_grade.ID is
'主键';

comment on column ims.test_grade.version is
'版本号';

comment on column ims.test_grade.emp_id is
'员工编号';

comment on column ims.test_grade.emp_name is
'员工姓名';

comment on column ims.test_grade.PAPER_GRADE is
'笔试成绩';

comment on column ims.test_grade.COM_GRADE_ROAD is
'机试成绩(道路)';

comment on column ims.test_grade.COM_GRADE_POI is
'机试成绩(设施)';

comment on column ims.test_grade.TOTAL_GRADE is
'综合成绩';

comment on column ims.test_grade.create_time is
'创建时间';

comment on column ims.test_grade.creator_id is
'创建人ID';

comment on column ims.test_grade.creator is
'创建人';

comment on column ims.test_grade.update_time is
'更新时间';

comment on column ims.test_grade.updater_id is
'更新人ID';

comment on column ims.test_grade.updater is
'更新人';

comment on column ims.test_grade.status is
'逻辑删除标识';

/*==============================================================*/
/* Table: user_role_rel                                         */
/*==============================================================*/
create table ims.user_role_rel  (
   rel_id               VARCHAR2(50)                    not null,
   username             VARCHAR2(50),
   role_id              VARCHAR2(50),
   constraint PK_USER_ROLE_REL primary key (rel_id)
);

