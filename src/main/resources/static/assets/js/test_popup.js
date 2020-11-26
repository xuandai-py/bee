$(document).ready(function() {
    $('a.login-window').click(function() {
        //lấy giá trị thuộc tính href - chính là phần tử "#login-box"
        var loginBox = $(this).attr('href');

        //cho hiện hộp đăng nhập trong 300ms
        $(loginBox).fadeIn(300);

        // thêm phần tử id="over" vào sau body
        $('body').append('<div id="over">');
        $('#over').fadeIn(300);

        return false;
    });

    // khi click đóng hộp thoại
    $(document).on('click', "a.close, #over", function() {
        $('#over, .login').fadeOut(300, function() {
            $('#over').remove();
        });
        return false;
    });
});

$(document).ready(function() {
    $('a.logup-window').click(function() {
        //lấy giá trị thuộc tính href - chính là phần tử "#login-box"
        var logupBox = $(this).attr('href');

        //cho hiện hộp đăng nhập trong 300ms
        $(logupBox).fadeIn(300);

        // thêm phần tử id="over" vào sau body
        $('body').append('<div id="over">');
        $('#over').fadeIn(300);

        return false;
    });

    // khi click đóng hộp thoại
    $(document).on('click', "a.close, #over", function() {
        $('#over, .login').fadeOut(300, function() {
            $('#over').remove();
        });
        return false;
    });
});

var input = document.querySelector('.pswrd');
var show = document.querySelector('.show');
show.addEventListener('click', active);

function active() {
    if (input.type === "password") {
        input.type = "text";
        show.style.color = "#1DA1F2";
        show.textContent = "HIDE";
    } else {
        input.type = "password";
        show.textContent = "SHOW";
        show.style.color = "#111";
    }
}