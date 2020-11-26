window.onload = function () {
    setTimeout("switch_Image()", 500);
}
var current = 1;
var num_image = 2;

function switch_Image() {
    current++;
    document.images['image'].src = '/images/logo-' + current + '.png';
    if (current < num_image) {
        setTimeout("switch_Image()", 500);
    } else if (current == num_image) {
        current = 0;
        setTimeout("switch_Image()", 500);
    }
}
