<%@ page import="com.ltf.dao.Reg" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body{
            width: 100%;
            height: 100%;
            background: url("bg.jpg") no-repeat;
            background-size: cover;
        }
        .reg-form{
            margin: 100px auto 0;
            padding: 35px 20px;
            width: 400px;
            /*border: 1px solid #ccc;*/
        }
        .form-item{
            display: block;
            overflow: hidden;
        }
        .form-item + .form-item{
            margin-top: 15px;
        }
        .form-item > strong{
            float: left;
            margin-right: 10px;
            width: 90px;
            line-height: 35px;
            text-align: right;
            color: #82d9fd;
        }
        .form-item > div{
            overflow: hidden;
        }
        .form-item > div input{
            padding: 8px 15px;
            width: 200px;
            opacity: 0.6;
        }
        .form-item > div i{
            margin-left: 10px;
            padding-top: 8px;
            position: absolute;
        }
        .form-item > div i.error{
            color: #f60;
        }
        .form-item > div i.success{
            width: 34px;
            height: 34px;
            background: url("2.png") no-repeat;
        }
        .sub-btn{
            padding-left: 100px;
        }
        .sub-btn button{
            padding: 12px 30px;
            color: #fff;
            font-size: 16px;
            width: 234px;
            text-align: center;
            background-color: #15b7ff;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<%
    Reg reg = (Reg)request.getAttribute("reg");
%>

<form class="reg-form" id="reg-form" method="post">
    <input type="hidden" value="<%=reg.getId()%>" name="id">
    <label class="form-item">
        <strong>
            手机号
        </strong>
        <div>
            <input type="tel" name="tpl" maxlength="11" value="<%=reg.getTpl()%>">
            <i></i>
        </div>
    </label>
    <label class="form-item">
        <strong>
            昵称
        </strong>
        <div>
            <input type="text" name="nickname" maxlength="11" value="<%=reg.getNickname()%>">
            <i></i>
        </div>
    </label>
    <label class="form-item">
        <strong>
            密码
        </strong>
        <div>
            <input type="password" name="password" maxlength="16" value="<%=reg.getPassword()%>">
            <i></i>
        </div>
    </label>
    <%--<label class="form-item">--%>
        <%--<strong>--%>
            <%--确认密码--%>
        <%--</strong>--%>
        <%--<div>--%>
            <%--<input type="password" name="re_password" maxlength="16" value="<%=reg.getPassword()%>">--%>
            <%--<i></i>--%>
        <%--</div>--%>
    <%--</label>--%>
    <label class="form-item">
        <strong>
            邮箱
        </strong>
        <div>
            <input type="email" name="email" value="<%=reg.getEmail()%>">
            <i></i>
        </div>
    </label>
    <label class="form-item">
        <div class="sub-btn">
            <button>提交修改</button>
        </div>
    </label>
</form>

<script type="text/javascript">

    var oForm = document.getElementById('reg-form'),
        oInputs = oForm.getElementsByTagName('input');

    //需要匹配正则的列
    var checkAttr = [
        {
            inspect : oInputs[0],//需要验证的input标签
            hints : oInputs[0].nextElementSibling//提示文本的节点

        }
        ,
        {
            inspect : oInputs[1],//需要验证的input标签
            hints : oInputs[1].nextElementSibling,//提示文本的节点
            check : {
                type : 'reg',//默认使用正则匹配
                reg : '^1[3-9]\\d{9}$',
                success : '',
                error : '手机号验证失败'
            }
        },
        {
            inspect : oInputs[2],//昵称
            hints : oInputs[2].nextElementSibling,//提示文本的节点
            check : {
                type :'reg',//正则匹配
                reg : '^[\\w|\\u4e00-\\u9fa5]{2,6}$',//昵称 中文 英文 数字 _
                success : '',
                error : '昵称验证失败'
            }
        },
        {
            inspect : oInputs[3],//密码
            hints : oInputs[3].nextElementSibling,//提示文本的节点
            check : {
                type :'reg',//正则匹配
                reg : '^([\\w|\\.|#|@|,|\\?|\\$]){8,16}$',//英文 数字 _ . # @ ?
                success : '',
                error : '密码只能包含 字母 数字 _ . # @ ?并且长度为8-16位'
            }
        },
//        {
//            inspect : oInputs[4],//确认密码
//            hints : oInputs[4].nextElementSibling,//提示文本的节点
//            check : {
//                type :'inspect',//对比
//                check : oInputs[2],
//                success : '',
//                error : '两次密码不一致'
//            }
//        },
        {
            inspect : oInputs[4],//邮箱
            hints : oInputs[4].nextElementSibling,
            check : {
                type :'reg',//对比
                reg : '^\\w+@\\w{2,10}\\.\\w{2,6}(\\.\\w{2,6})?$',//dsdasd@xx.xxx 13123123@com.cn
                success : '',
                error : '邮箱验证'
            }
        }
    ];

    //验证input
    for(var i = 0,iL = checkAttr.length;i < iL;i++){
        (function(thatAttr){
            thatAttr.inspect.addEventListener('blur', function(){
                //判断是否为正则匹配
                if(thatAttr.check.type == 'reg'){
                    this.statu = new RegExp(thatAttr.check.reg).test(this.value);
                    hintsShow(thatAttr.hints,this.statu,this.statu ? thatAttr.check.success : thatAttr.check.error);
                }
                //两者之间进行对比
                if(thatAttr.check.type == 'inspect'){
                    this.value = this.value.replace(/^\s+|\s+$/g,'');
                    this.statu = this.value != '' && this.value == thatAttr.check.check.value;
                    hintsShow(thatAttr.hints,this.statu,this.statu ? thatAttr.check.success : thatAttr.check.error);
                }
            });
        })(checkAttr[i]);
    }
    //默认第一个获取焦点，防止用户一进来就提交
    oInputs[0].focus();
    //表单提交
    oForm.addEventListener('submit',function(e){



        for (var i = 0,iL = oInputs.length; i < iL;i++) {
            oInputs[i].focus();
            oInputs[i].blur();//主动失去焦点触发
        }
        var errors = this.getElementsByClassName('error');

        if(errors.length === 0){

            console.log('验证成功可以提交')
            oForm.action = "save";
            oForm.submit();
        }else{

            console.log('验证失败不可以提交')
        }
        e.preventDefault();
    });

    function hintsShow(ele,blooe,str){
        ele.className = blooe ? 'success' : 'error';
        ele.innerHTML = str;
    }
</script>
</body>
</html>

