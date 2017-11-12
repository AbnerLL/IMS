/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2017/10/15 18:19:15                          */
/*==============================================================*/


alter table ROLE_PERMISSION_REL
   drop constraint FK_ROLE_PER_REFERENCE_SYS_ROLE;

alter table ROLE_PERMISSION_REL
   drop constraint FK_ROLE_PER_REFERENCE_SYS_PERM;

alter table USER_ROLE_REL
   drop constraint FK_USER_ROL_REFERENCE_SYS_USER;

alter table USER_ROLE_REL
   drop constraint FK_USER_ROL_REFERENCE_SYS_ROLE;

drop table ROLE_PERMISSION_REL cascade constraints;

drop table SYS_PERMISSION cascade constraints;

drop table SYS_ROLE cascade constraints;

drop table SYS_USER cascade constraints;

drop table USER_ROLE_REL cascade constraints;

/*==============================================================*/
/* Table: ROLE_PERMISSION_REL                                   */
/*==============================================================*/
create table ROLE_PERMISSION_REL  (
   rel_id               VARCHAR2(50)                    not null,
   role_id              VARCHAR2(50),
   permission_id        VARCHAR2(50),
   create_time          DATE,
   constraint PK_ROLE_PERMISSION_REL primary key (rel_id)
);

comment on table ROLE_PERMISSION_REL is
'角色权限表';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table SYS_PERMISSION  (
   permission_id        VARCHAR2(50)                    not null,
   parent_id            VARCHAR2(50),
   name                 VARCHAR2(200),
   description          VARCHAR2(500),
   constraint PK_SYS_PERMISSION primary key (permission_id)
);

comment on table SYS_PERMISSION is
'权限表';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE  (
   role_id              VARCHAR2(50)                    not null,
   parent_id            VARCHAR2(50),
   role_name            VARCHAR2(200),
   create_time          DATE,
   description          VARCHAR2(500),
   constraint PK_SYS_ROLE primary key (role_id)
);

comment on table SYS_ROLE is
'角色表';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER  (
   user_id              VARCHAR2(50)                    not null,
   group_id             VARCHAR2(50),
   username             VARCHAR2(200),
   password             VARCHAR2(200),
   nickname             VARCHAR2(500),
   mobile               VARCHAR2(20),
   tel                  VARCHAR2(20),
   email                VARCHAR2(200),
   create_time          DATE,
   status               CHAR(1),
   attr1                VARCHAR2(200),
   attr2                VARCHAR2(200),
   attr3                VARCHAR2(200),
   attr4                VARCHAR2(200),
   attr5                VARCHAR2(200),
   constraint PK_SYS_USER primary key (user_id)
);

comment on table SYS_USER is
'用户表';

/*==============================================================*/
/* Table: USER_ROLE_REL                                         */
/*==============================================================*/
create table USER_ROLE_REL  (
   rel_id               VARCHAR2(50)                    not null,
   user_id              VARCHAR2(50),
   role_id              VARCHAR2(50),
   create_time          DATE,
   constraint PK_USER_ROLE_REL primary key (rel_id)
);

comment on table USER_ROLE_REL is
'用户角色表';

alter table ROLE_PERMISSION_REL
   add constraint FK_ROLE_PER_REFERENCE_SYS_ROLE foreign key (role_id)
      references SYS_ROLE (role_id);

alter table ROLE_PERMISSION_REL
   add constraint FK_ROLE_PER_REFERENCE_SYS_PERM foreign key (permission_id)
      references SYS_PERMISSION (permission_id);

alter table USER_ROLE_REL
   add constraint FK_USER_ROL_REFERENCE_SYS_USER foreign key (user_id)
      references SYS_USER (user_id);

alter table USER_ROLE_REL
   add constraint FK_USER_ROL_REFERENCE_SYS_ROLE foreign key (role_id)
      references SYS_ROLE (role_id);

