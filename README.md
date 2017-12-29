# deploy
```
product：Ip:1225
java -jar *.jar --spring.profiles.active=test
java -jar *.jar --spring.profiles.active=prod
```

# API:


## header

- ~~authorization:username:?password?~~

## response code info
code | msg
---|---
S0000 | success
S0001 | system error

### response success:
    HttpStatus == 200
```json
{
    "msg": "success",
    "code": "S0000"
}
```
### response unsuccess:
     HttpStatus:500

```json
{
    "msg": "system error",
    "code": "S0001"
}
```

    


## 1、Create email template

[http://ip:1225/email/create](http://ip:1225/email/create)

- content-type:application/json
- method:POST


fields | 含义 | type | demo
---|---|---|---
name | 模版名称 | String | user
desc | 模版描述 | String | username change
source_html | html头部 | Stirng | --

---

## request:
```json
{
	"name":"user",
	"desc":"username change",
	"source_html":"<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
}
```

---



## 2、modify email template

[http://ip:1225/email/modify](http://ip:1225/email/modify)

- content-type:application/json
- method:POST


fields | 含义 | type | demo
---|---|---|---
_id | 模版id | String | 必填
name | 模版名称 | String | 密码
desc | 模版描述 | String | 密码修改
source_html | html头部 | Stirng | --

---

### request:

```json
删除操作
{
	"status":"0",
	"_id":"5a41ebab411bf80f8a4e3b06"
}

更新操作

{
	"_id":"5a41ebab411bf80f8a4e3b06",
	"source_html":"<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
}
```


## 3、query email template

[http://ip:1225/email/list/{page}/{size}](http://ip:1225/email/list/{page}/{size})

- content-type:application/json
- method:POST

---
### request：

fields | 含义 | type | demo
---|---|---|---
page | 页数 | int | 0 开始
size | 每页几条记录 | int | 1


```
localhost:1225/email/list/2/3
```


---

### response

fields | 含义 | type | demo
---|---|---|---
name | 模版名称 | String | 密码
desc | 模版描述 | String | 密码修改
createTime | 模版创建时间 | String | ---
updateTime | 模版更新时间 | String | ---
sourceHtml | html头部 | Stirng | --
totalElements | 总记录数 | int | --
totalPages | 总页数 | Stirng | --
paramsEntityMap | filed 为需要传递的参数 | map | --


```json
{
    "msg": "success",
    "code": "S0000",
    "content": {
        "content": [
            {
                "_id": "5a41ecdc411bf8102afbeefd",
                "name": "userss",
                "desc": "username chenge",
                "paramsEntityMap": {
                    "1": {
                        "fileds": "name",
                        "content": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:"
                    },
                    "2": {
                        "fileds": "code",
                        "content": ",code:"
                    },
                    "3": {
                        "fileds": "",
                        "content": ",image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
                    }
                },
                "sourceHtml": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>",
                "status": "1",
                "createTime": 1514269916754,
                "updateTime": 1514269916754
            },
            {
                "_id": "5a41ecdc411bf8102afbeefc",
                "name": "userss",
                "desc": "username chenge",
                "paramsEntityMap": {
                    "1": {
                        "fileds": "name",
                        "content": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:"
                    },
                    "2": {
                        "fileds": "code",
                        "content": ",code:"
                    },
                    "3": {
                        "fileds": "",
                        "content": ",image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
                    }
                },
                "sourceHtml": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>",
                "status": "1",
                "createTime": 1514269918670,
                "updateTime": 1514269918670
            }
        ],
        "totalPages": 2,
        "last": true,
        "totalElements": 5,
        "size": 3,
        "number": 1,
        "sort": null,
        "first": false,
        "numberOfElements": 2
    }
}

```


## 4、search email template

[http://ip:1225/email/search/{name}](http://ip:1225/email/search/{name})

- content-type:application/json
- method:POST

---
### request：

fields | 含义 | type | demo
---|---|---|---
name | 模版名称 | string | userss


```
localhost:1225/email/search/userss
```

---

### response

fields | 含义 | type | demo
---|---|---|---
name | 模版名称 | String | 密码
desc | 模版描述 | String | 密码修改
createTime | 模版创建时间 | String | ---
updateTime | 模版更新时间 | String | ---
sourceHtml | html头部 | Stirng | --


```json
{
    "msg": "success",
    "code": "S0000",
    "content": [
        {
            "_id": "5a41ec24411bf80f8a4e3b07",
            "name": "userss",
            "desc": "username chenge",
            "paramsEntityMap": {
                "1": {
                    "fileds": "",
                    "content": "null"
                }
            },
            "sourceHtml": null,
            "status": "1",
            "createTime": 1514269732985,
            "updateTime": 1514269732985
        },
        {
            "_id": "5a41ecdc411bf8102afbeefd",
            "name": "userss",
            "desc": "username chenge",
            "paramsEntityMap": {
                "1": {
                    "fileds": "name",
                    "content": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:"
                },
                "2": {
                    "fileds": "code",
                    "content": ",code:"
                },
                "3": {
                    "fileds": "",
                    "content": ",image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
                }
            },
            "sourceHtml": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>",
            "status": "1",
            "createTime": 1514269916754,
            "updateTime": 1514269916754
        },
        {
            "_id": "5a41ecdc411bf8102afbeefc",
            "name": "userss",
            "desc": "username chenge",
            "paramsEntityMap": {
                "1": {
                    "fileds": "name",
                    "content": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:"
                },
                "2": {
                    "fileds": "code",
                    "content": ",code:"
                },
                "3": {
                    "fileds": "",
                    "content": ",image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>"
                }
            },
            "sourceHtml": "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code},image:<image src='http://img02.tooopen.com/images/20160408/tooopen_sy_158723161481.jpg'/></div></body></html></body></html>",
            "status": "1",
            "createTime": 1514269918670,
            "updateTime": 1514269918670
        }
    ]
}

```

# 2、发送邮件

## 2.1、send email

[http://ip:1225/sendgrid-email/send](http://ip:1225/sendgrid-email/send)

- content-type:application/json
- method:POST

---
### request：

fields | 含义 | type | demo
---|---|---|---
send_id | 模版id | string(必填) | 5a40c7a2411bf80ab8a99b84
from | 发送人 | string (必填)| wecash@gmail.com
subject| 主题 | string(必填) | 修改密码
email_bodys| email_content:邮件内容sb:接收人 | list(必填) | [{"sb": "123@gmail.com","email_content": {"name": "user","code": "213467" }}]


```json
{
    "send_id": "5a41ebab411bf80f8a4e3b06",
    "from": "lishulong@gmail.com",
    "subject": "这是一个主题测试",
    "email_bodys": [
        {
            "sb": "xxxxxxx@xxx.net",
            "email_content": {
                "name": "yuanshuwei",
                "code": "213467"
            }
        }
    ]
}

```

---

### response:
    httpstatus：400

```json
{
    "msg": "sendId not exist",
    "code": "E0001"
}

{
    "msg": "from not null",
    "code": "E0002"
}

{
    "msg": "to sb not null",
    "code": "E0003"
}

{
    "msg": "exit send error",
    "code": "E0004",
    "unsuccess":["23@gmail.com","12@gmail.com"]
}
```

## 2.2、send email template

[http://ip:1225/sendgrid-email/send](http://ip:1225/sendgrid-email/send)

- content-type:application/json
- method:POST

---
### request：

fields | 含义 | type | required | demo
---|---|---|---|---
template_id | 模版id | string | true | 75a831b9-9d96-4501-ad3d-52a76381151c
from | 发送人 | string | true |-
from.name | 发送人名 | string | false |-
from.email | 发送人邮箱 | string | true |-
personalizations | 发送内容 | array | true | -
personalizations[x].to[x].name | 收件人名 | string | false | -
personalizations[x].to[x].email | 收件人邮箱 | string | true | -
personalizations[x].subject | 主题 | string | true | -
personalizations[x].substitutions | 模版key替换为value | map | true | -
personalizations[x].bbc | 密送 | object | false | -


```json
{
  "personalizations": [
    {
      "to": [
        {
          "email": "lishulong@wecash.net"
        }
      ],
      "bcc":[{
      	"name":"qingsheng",
      	"email":"yuanshuwei@wecash.net"
      }],
      "subject": "good, 沈一阵....",
      "substitutions":{
        ":name": "Lee shu shu",
        "%age%": "123",
        ":content":"这是内容："
      }
    }
  ],
  "from": {
  	"name":"申遗珍",
    "email": "from_address@example.com"
  },
  
  "template_id":"75a831b9-9d96-4501-ad3d-52a76381151c11"
}

```

---

### response:
    httpstatus：400

```json
{
    "msg": "email templateId must not null",
    "code": "E0005"
}

{
    "msg": "exit send error",
    "code": "E0004",
    "unsuccess": [
        "Request returned status Code 400Body:{\"errors\":[{\"message\":\"The template_id must be a valid GUID, you provided '75a831b9-9d96-4501-ad3d-52a76381151c11'.\",\"field\":\"template_id\",\"help\":\"http://sendgrid.com/docs/API_Reference/Web_API_v3/Mail/errors.html#message.template_id\"},{\"message\":\"Unless a valid template_id is provided, the content parameter is required. There must be at least one defined content block. We typically suggest both text/plain and text/html blocks are included, but only one block is required.\",\"field\":\"content\",\"help\":\"http://sendgrid.com/docs/API_Reference/Web_API_v3/Mail/errors.html#message.content\"}]}"
    ]
}
```
