document.addEventListener('DOMContentLoaded', function () {
    const images = document.querySelectorAll('.product-image-list img');
    const imageCountBox = document.querySelector('.image-count-box');
    const prevButton = document.querySelector('.prev-button');
    const nextButton = document.querySelector('.next-button');
    const zoomButton = document.querySelector('.zoom-button');
    const productImageWrapper = document.querySelector('.product-image-wrapper');
    const modal = document.getElementById('zoom-image-modal');
    const modalImage = modal.querySelector('.modal-image');
    const modalImageBox = modal.querySelector('.modal-image-box');
    const modalImageCountBox = modal.querySelector('.modal-footer-count-box');
    const exitModalButton = modal.querySelector('.exit-modal');
    const categoryBox = document.querySelector('.category-box');
    const categoryList = document.querySelector('.category-list');
    const categoryMenus = document.querySelectorAll('.category-list a')
    const selectedCategoryDiv = document.querySelector('.selected-category');
    const categoryValue = selectedCategoryDiv.textContent.trim();
    const likeBox = document.querySelector('.like-box');
    const likeButton = document.querySelector('.like');
    const likeImg = document.querySelector('.like img');
    let currentIndex = 0;


    function updateSlider() {
        images.forEach((img, index) => {
            img.style.opacity = index === currentIndex ? '1' : '0';
        });
        const buttons = imageCountBox.querySelectorAll('button');
        buttons.forEach((button, index) => {
            button.className = index === currentIndex ? 'image-circle-button-active' : 'image-circle-button';
        });
        prevButton.style.display = currentIndex === 0 ? 'none' : 'flex';
        nextButton.style.display = currentIndex === images.length - 1 ? 'none' : 'flex';
    }

    function updateModalSlider() {
        modalImage.src = images[currentIndex].src;
        const buttons = modalImageCountBox.querySelectorAll('button');
        buttons.forEach((button, index) => {
            button.className = index === currentIndex ? 'modal-image-circle-button-active' : 'modal-image-circle-button';
        });
    }

    images.forEach((_, index) => {
        const button = document.createElement('button');
        button.className = 'image-circle-button';
        button.addEventListener('mouseenter', () => {
            if (index !== currentIndex) {
                button.style.backgroundColor = '#888888';
            }
        });
        button.addEventListener('mouseleave', () => {
            button.style.backgroundColor = '';
        });
        button.addEventListener('click', () => {
            currentIndex = index;
            updateSlider();
        });
        imageCountBox.appendChild(button);

        const modalButton = document.createElement('button');
        modalButton.className = 'modal-image-circle-button';
        modalButton.addEventListener('click', () => {
            currentIndex = index;
            updateModalSlider();
        });
        modalImageCountBox.appendChild(modalButton);
    });

    prevButton.addEventListener('click', () => {
        if (currentIndex > 0) {
            currentIndex--;
            updateSlider();
        }
    });

    nextButton.addEventListener('click', () => {
        if (currentIndex < images.length - 1) {
            currentIndex++;
            updateSlider();
        }
    });

    zoomButton.addEventListener('click', () => {
        updateModalSlider();
        modal.style.display = 'flex';
    });

    exitModalButton.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    modal.addEventListener('click', (event) => {
        const isClickInsideImageBox = modalImageBox.contains(event.target);
        const isClickInsideCountBox = modalImageCountBox.contains(event.target);
        const isClickInsideExitButton = exitModalButton.contains(event.target);

        if (!isClickInsideImageBox && !isClickInsideCountBox && !isClickInsideExitButton) {
            modal.style.display = 'none';
        }
    });

    productImageWrapper.addEventListener('mouseenter', () => {
        prevButton.style.display = currentIndex === 0 ? 'none' : 'flex';
        nextButton.style.display = currentIndex === images.length - 1 ? 'none' : 'flex';
        zoomButton.style.display = 'flex';
    });

    productImageWrapper.addEventListener('mouseleave', () => {
        prevButton.style.display = 'none';
        nextButton.style.display = 'none';
        zoomButton.style.display = 'none';
    });

    categoryBox.addEventListener('mouseenter', () => {
        categoryList.style.display = 'block';
    });
    categoryBox.addEventListener('mouseleave', () => {
        categoryList.style.display = 'none';
    });

    categoryMenus.forEach((menu, index) => {

        if (categoryValue === menu.textContent.trim()) {
            menu.style.color = 'red';
        }
    });

    likeBox.addEventListener('click', () => {

        const wishedColor = 'rgb(51,51,51)';
        const unwishedColor = 'rgb(204,204,204)';
        const wishedHeartImg = '/image/wished_icon.png';
        const unwishedHeartImg = '/image/unwished_icon.png';

        //if 찜x 상태면 찜db추가, 하트 이미지 변경, 배경색 변경

        // //else(찜 상태)면 찜db제거, 하트 이미지 변경, 배경색 변경
        if (likeImg.src === 'http://localhost:8080/image/unwished_icon.png') {
            likeButton.style.backgroundColor = wishedColor;
            likeImg.src = wishedHeartImg;
        } else {
            likeButton.style.backgroundColor = unwishedColor;
            likeImg.src = unwishedHeartImg;
        }

    });



    updateSlider();
});
