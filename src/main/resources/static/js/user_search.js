
$(document).ready(function() {

    // 페이지 로드 시 바로 호출
    init();

    // 회원 검색 기록
    function init(){
        let userId = 1;

        $.ajax({
            url: 'search/init?userId='+userId,
            type: 'GET',
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

    // 검색 후 해당 상품 리스트 출력
    function search() {
        let query = $('#searchInput').val();
        if (query) {
            // 여기에 검색 로직을 추가하세요.
            insertContent(query);
            console.log("Query inserted, now fetching items...");
            // 이후 상품 출력하기
            location.href = "list/items?content="+query;

        } else {
            alert('Please enter a search term.');
        }
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


});
// 검색 데이터 클릭 이벤트
function getContent(contentBtn){
    let content = $(contentBtn).text();
    console.log("검색 목록 content : "+content);
    location.href = "list/items?content="+content;
}

// 검색 데이터 delete
function deleteContent(btn){
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

