function base(){
    var app = new Vue({
        el: "#app"
    })
}

// view : {{message}} 로 접근 가능하다.
// console : console.log(app.message)
function textBinding(){
    var app = new Vue({
        el : "#app",
        data : {
            message : "Hello world!"
        }
    })
}

function forBinding(){
    var app = new Vue({
        el : "#app",
        data : {
            items : ['사과', '바나나', '딸기']
        }
    })
}