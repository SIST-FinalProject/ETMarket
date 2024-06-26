function toggleArrow() {
    var selectBox = document.getElementById('status-select');
    var arrowIcon = document.getElementById('arrow-icon');

    if (selectBox.classList.contains('open')) {
        arrowIcon.classList.remove('bi-chevron-up');
        arrowIcon.classList.add('bi-chevron-down');
        selectBox.classList.remove('open');
    } else {
        arrowIcon.classList.remove('bi-chevron-down');
        arrowIcon.classList.add('bi-chevron-up');
        selectBox.classList.add('open');
    }
}

// select 박스를 클릭할 때에도 화살표가 바뀌도록 이벤트 리스너 추가
document.getElementById('status-select').addEventListener('click', function() {
    var arrowIcon = document.getElementById('arrow-icon');
    if (arrowIcon.classList.contains('bi-chevron-down')) {
        arrowIcon.classList.remove('bi-chevron-down');
        arrowIcon.classList.add('bi-chevron-up');
    } else {
        arrowIcon.classList.remove('bi-chevron-up');
        arrowIcon.classList.add('bi-chevron-down');
    }
});
