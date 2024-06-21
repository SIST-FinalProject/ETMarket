
$(document).ready(function() {

    // 페이지 로드 시 바로 호출
    init();

    $('#items-list').hide();

    // 회원 검색 기록
    function init(){
        let userId = 1;

        $.ajax({
            url: 'search/init?userId='+userId,
            type: 'POST',
            contentType: 'application/json',
            success: function (response) {
                console.log("Initial search content:", response);
                top8_search(response);
            },
            error: function (xhr, status, error) {
                console.error("Error loading initial content:", error);
                console.error("Status:", status);
                console.error("XHR response text:", xhr.responseText);
            }
        })
    }

    // 검색 후......
    function search() {
        let query = $('#searchInput').val();
        if (query) {
            // 여기에 검색 로직을 추가하세요.
            insertContent(query);
            console.log("Query inserted, now fetching items...");
            searchResult();

        } else {
            alert('Please enter a search term.');
        }
    }

    // 검색 후 상품 리스트 가져옴
    function searchResult() {
        let content = $('#searchInput').val(); // content 값을 입력 필드에서 가져옴
        $.ajax({
            url: 'search/items',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            data: {"content": content},
            success: function (response){
                // $('#items-list').empty();
                console.log("Content items:", response);
                alert("Content items fetched successfully");
                // response 데이터 사용 로직 추가
                $('#items-list').show();
            },
            error: function (xhr, status, error) {
                console.error("Error occurred while fetching items: ", status, error);
            }
        });
    }


    // 검색 데이터 insert
    function insertContent(content){
        let data = {
            userId: 1, // 의미로 설정
            content: content
        };
        $.ajax({
            url: 'search/insert',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response){
                console.log("Content inserted");
                // init();
            }
        })
    }

    // 회원 검색 기록 html에 출력
    function top8_search(data) {
        let container = $('.bCNTNM');
        container.empty();

        data.forEach(function (item, index){
            var str = '<div class="fcMDtU">' +
                '<button type="button" id="content' + index + '" onclick="getContent(this)">' + item.content + '</button>' +
                '<button type="button" class="gUWbGN" value="'+item.content+'" onclick="deleteContent(this)" ><i class="bi bi-x"></i></button>' +
                '</div>'

            container.append(str);
        })
    }

    // 검색창 엔터 키 이벤트
    $('#searchInput').keypress(function(event) {
        if (event.which === 13) { // 13은 엔터 키의 키 코드입니다.
            console.log("엔터 인식됨");
            search();
        }
    });

    // 돋보기 클릭 이벤트
    $('#searchButton').click(function() {
        console.log("클릭 인식됨");
        search();
    });

    // 검색 데이터 클릭 이벤트
    window.getContent = function(contentBtn){
        let content = $(contentBtn).text();
        console.log("검색 목록 content : " + content);
        searchResult(content); // AJAX 요청으로 검색 결과를 가져옵니다.
    }

    // 검색 데이터 delete
    window.deleteContent = function(btn){
        let content = $(btn).val();
        $.ajax({
            url: 'search/delete',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({"content": content}),
            success: function (response){
                console.log("Content Delete success");
                location.reload(true);
            },
            error: function (xhr, status, error) {
                console.error("Error deleting content:", error);
                console.error("Status:", status);
                console.error("XHR response text:", xhr.responseText);
            }
        })
    }

});


