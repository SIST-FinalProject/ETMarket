document.addEventListener('DOMContentLoaded', function() {
    const modifyButton = document.querySelector('.btn-modify-introduce');
    const storeDescription = document.querySelector('.store-description');
    const modifyMypageBox = document.querySelector('.modify-mypage-box');
    const modifyContainer = document.getElementById('modify-container');
    const confirmButton = document.getElementById('confirm-button');
    const charCount = document.getElementById('char-count');
    const textarea = document.getElementById('store-description-textarea');

    modifyButton.addEventListener('click', function() {
        storeDescription.classList.add('hidden');
        modifyMypageBox.classList.add('hidden');
        modifyContainer.classList.remove('hidden');
        modifyContainer.classList.add('modify-div');
    });

    textarea.addEventListener('input', function() {
        charCount.textContent = `${textarea.value.length}/1000`;
    });

    confirmButton.addEventListener('click', function() {
        const descriptionText = textarea.value;

        $.ajax({
            url: '/update-introduce',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ description: descriptionText }),
            success: function(response) {
                storeDescription.innerText = response.introduction;
                storeDescription.classList.remove('hidden');
                modifyMypageBox.classList.remove('hidden');
                modifyContainer.classList.add('hidden');
                modifyContainer.classList.remove('modify-div');
            },
            error: function() {
                alert('소개글 수정 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
});