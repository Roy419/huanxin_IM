package com.goodsure.frameworkdemo.common;

import com.hyphenate.EMError;

/**
 * Created by Administrator on 2018/5/31.
 */

public class ErrorContent {

    public static  String showError(int code){
        String errorName ="";
        switch (code){
            case  EMError.EM_NO_ERROR :
                errorName ="无错误";
                break;
            case
             EMError.GENERAL_ERROR :
                errorName= "常规错误" ;
                break;
            case EMError.NETWORK_ERROR:
            errorName= "网络错误" ;
            break;
            case  EMError.EXCEED_SERVICE_LIMIT:
            errorName= "超出服务限制" ;
            break;
            case  EMError.INVALID_APP_KEY:
            errorName= "无效的应用key" ;
            break;
            case  EMError.INVALID_USER_NAME :
             errorName= "无效的用户名" ;
            break;
            case  EMError.INVALID_PASSWORD :
            errorName= "无效的密码" ;
            break;
            case   EMError.INVALID_URL :
            errorName= "无效的URL" ;
            break;
            case  EMError.INVALID_TOKEN:
            errorName= "无效的令牌" ;
            break;
            case  EMError.USER_ALREADY_LOGIN :
            errorName= "用户已经登录" ;
            break;
            case  EMError.USER_NOT_LOGIN :
            errorName= "用户没有登录" ;
            break;
            case  EMError.USER_AUTHENTICATION_FAILED:
            errorName= "用户名或密码错误" ;
            break;
            case EMError.USER_ALREADY_EXIST :
            errorName= "用户已经存在" ;
            break;
            case  EMError.USER_NOT_FOUND :
            errorName= "户名不存在" ;
            break;
            case   EMError.USER_ILLEGAL_ARGUMENT :
            errorName= "用户非法参数" ;
            break;
            case  EMError.USER_LOGIN_ANOTHER_DEVICE:
            errorName= "用户登录另一个设备" ;
            break;
            case EMError.USER_REMOVED :
            errorName= "用户删除" ;
            break;
            case EMError.USER_REG_FAILED:
            errorName= "用户注册失败" ;
            break;
            case   EMError.USER_UPDATEINFO_FAILED :
            errorName= "用户修改信息失败" ;
            break;
            case  EMError.USER_PERMISSION_DENIED:
            errorName= "用户没有权限" ;
            break;
            case EMError.USER_BINDDEVICETOKEN_FAILED:
            errorName= "用户绑定设备失败" ;
            break;
            case EMError.USER_UNBIND_DEVICETOKEN_FAILED :
            errorName= "用户解绑设备失败" ;
            break;
            case  EMError.USER_BIND_ANOTHER_DEVICE :
            errorName= "用户绑定另一个设备" ;
            break;
            case  EMError.USER_LOGIN_TOO_MANY_DEVICES:
            errorName= "用户登录设备太多了" ;
            break;
            case  EMError.USER_MUTED :
            errorName= "用户静音" ;
            break;
            case EMError.USER_KICKED_BY_CHANGE_PASSWORD:
            errorName= "用户在别处登录，判断是否本人操作，如果不是请更改密码。" ;
            break;
            case  EMError.USER_KICKED_BY_OTHER_DEVICE:
            errorName= "用户踢其他设备" ;
            break;
            case EMError.SERVER_NOT_REACHABLE:
            errorName= "服务器无法访问" ;
            break;
            case  EMError.SERVER_TIMEOUT :
            errorName= "服务器超时" ;
            break;
            case  EMError.SERVER_BUSY :
            errorName= "服务器繁忙" ;
            break;
            case  EMError.SERVER_UNKNOWN_ERROR:
            errorName= "服务器未知错误" ;
            break;
            case  EMError.SERVER_GET_DNSLIST_FAILED:
            errorName= " 服务器得到DNSLIST失败" ;
            break;
            case  EMError.SERVER_SERVICE_RESTRICTED:
            errorName= "服务器服务限制" ;
            break;
            case EMError.FILE_NOT_FOUND:
            errorName= "文件未找到" ;
            break;
            case EMError.FILE_INVALID :
            errorName= "文件无效" ;
            break;
            case  EMError.FILE_UPLOAD_FAILED:
            errorName= "文件上传失败" ;
            break;
            case EMError.FILE_DOWNLOAD_FAILED:
            errorName= "文件下载失败" ;
            break;
            case  EMError.FILE_DELETE_FAILED :
            errorName= "文件删除失败" ;
            break;
            case  EMError.FILE_TOO_LARGE :
            errorName= "文件太大" ;
            break ;
            case  EMError.MESSAGE_INVALID :
            errorName= "信息无效" ;
            break;
            case  EMError.MESSAGE_INCLUDE_ILLEGAL_CONTENT:
            errorName= "消息包含非法内容" ;
            break;
            case  EMError.MESSAGE_SEND_TRAFFIC_LIMIT:
            errorName= "消息发送流量限制" ;
            break;
            case  EMError.MESSAGE_ENCRYPTION_ERROR:
            errorName= "消息加密错误" ;
            break;
            case   EMError.GROUP_INVALID_ID :
            errorName= "组无效ID" ;
            break;
            case  EMError.GROUP_ALREADY_JOINED :
            errorName= "已经加入分组" ;
            break;
            case EMError.GROUP_NOT_JOINED :
            errorName= "没有加入分组" ;
            break;
            case   EMError.GROUP_PERMISSION_DENIED :
            errorName= "组没有权限" ;
            break;
            case  EMError.GROUP_MEMBERS_FULL :
            errorName= "组成员爆满" ;
            break;
            case   EMError.GROUP_NOT_EXIST :
            errorName= "组不存在" ;
            break;
            case   EMError.CHATROOM_INVALID_ID :
            errorName= "聊天室无效ID" ;
            break;
            case EMError.CHATROOM_ALREADY_JOINED :
            errorName= "聊天室已经加入" ;
            break;
            case  EMError.CHATROOM_NOT_JOINED:
            errorName= "聊天室不加入" ;
            break;
            case  EMError.CHATROOM_PERMISSION_DENIED :
            errorName= " 聊天室没有权限" ;
            break;
            case  EMError.CHATROOM_MEMBERS_FULL :
            errorName= "聊天室成员爆满" ;
            break;
            case   EMError.CHATROOM_NOT_EXIST :
            errorName= "聊天室不存在" ;
            break;
            case  EMError.CALL_INVALID_ID :
            errorName= "调用无效ID" ;
            break;
            case EMError.CALL_BUSY :
            errorName= "电话忙" ;
            break;
            case  EMError.CALL_REMOTE_OFFLINE :
            errorName= "调用远程离线" ;
            break;
            case EMError.CALL_CONNECTION_ERROR:
            errorName= "电话连接错误" ;
            break;
            case  EMError.CALL_CONFERENCE_CREATE_FAILED:
            errorName= "电话会议创建失败" ;
            break;
            case EMError.CALL_CONFERENCE_CANCEL:
            errorName= "电话会议取消" ;
            break;
            case EMError.CALL_ALREADY_JOIN :
            errorName= "电话已经加入" ;
            break;
            case  EMError.CALL_ALREADY_PUB :
            errorName= "" ;
            break;
            case   EMError.CALL_ALREADY_SUB :
            errorName= "" ;
            break;
            case EMError.CALL_NO_SESSION :
            errorName= "电话没有会话" ;
            break;
            case  EMError.CALL_NO_PUBLISH:
            errorName= "电话没有发布" ;
            break;
            case EMError.CALL_NO_SUBSCRIBE :
            errorName= "调用没有订阅" ;
            break;
            case  EMError.CALL_NO_STREAM :
            errorName= "电话没有流" ;
            break;
            case  EMError.CALL_TICKET_INVALID :
            errorName= "票无效" ;
            break;
            case EMError.CALL_TICKET_EXPIRED:
            errorName= "票过期了" ;
            break;
            case   EMError.CALL_SESSION_EXPIRED :
            errorName= " 呼叫会话过期" ;
            break;
            case   EMError.CALL_CONFERENCE_NO_EXIST:
            errorName= " 电话会议不存在" ;
            break;
            case  EMError.CALL_INVALID_CAMERA_INDEX :
            errorName= "调用无效相机指数" ;
            break;
            case  EMError.CALL_INVALID_PARAMS:
            errorName= "调用无效的参数" ;
            break;
            case  EMError.CALL_CONNECTION_TIMEOUT :
            errorName= "电话连接超时" ;
            break;
            case  EMError.CALL_JOIN_TIMEOUT :
            errorName= "电话连接超时" ;
            break ;
            case  EMError.CALL_OTHER_DEVICE :
            errorName= "调用其他设备" ;
            break;
            case   EMError.CALL_CONFERENCE_DISMISS :
            errorName= "电话会议解散" ;
            break;
          }
        return errorName;

    }
}
