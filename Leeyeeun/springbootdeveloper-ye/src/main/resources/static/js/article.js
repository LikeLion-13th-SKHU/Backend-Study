// 등록 기능
// id 가 create-btn 인 엘리먼트 조회
const createButton = document.getElementById('create-btn')

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch(`/api/articles`, {
            method: `POST`,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then( () => {
                alert('등록이 완료되었습니다.');
                location.replace(`/articles`);
            });
    });
}

// 삭제 기능
// id가 delete-btn인 엘리먼트 조회
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) { // 해당 엘리먼트에서 클릭 이벤트 발생하면 fetch() 메서드로 delete 요청 보냄
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: `DELETE`
        })
            .then( () => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}

// 수정 기능
// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn')

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: `PUT`,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ // 클릭 이벤트 발생시 id가 title, content인 엘리먼트의 값을 가져와 요청 보냄
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then( () => {
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}