$(function() {
    $(".send").click(function () {
        sendInfo();
        $(".content").val("");
    });
    setInterval(syncInfo, 1000);
});
var content="";
function sendInfo(){
    var text=$(".content").val();
    var showText="我 : "+text;
    content =content+"<span>"+showText+"</span>";
    $(".talk").html(content);
    $.ajax({
        type:'POST',
        url:'indexServlet',
        data:{"content":text,date:new Date()}//返回服务器的数据
    });
}
function syncInfo() {
    var con;
    $.ajax({
        type:'GET',
        url:'indexServlet',
        dataType:'JSON',
        data:{
            date:new Date()
        },
        error:function (XMLHttpRequest,textStatus,errorThrown) {
            console.log(textStatus)
        },
        success:function (data) {
            con = data.talk;

            if(con==undefined){

            }else{
                content = content+"<span>"+con+"</span>";
                $(".talk").html(content);
                con = undefined;
            }
        }

    });
}
