function post() {
    var postId = $("#post_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
             "parentId": postId,
             "content": content,
             "type": 1
            }),
        success: function (response) {
                if (response.code == 200) {
                    $("#comment_section").hide();
                } if(response.code == 001) {
                    var isAccepted = window.confirm(response.message);
                    if(isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=1f653623fb8a6b3b6f54&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }
                else {
                    alert(response.message);
                }
                console.log(response);
            },
        dataType: "json"
    });
}