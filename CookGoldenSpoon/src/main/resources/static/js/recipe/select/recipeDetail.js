const like = document.getElementById("like");
const bookmark = document.getElementById("bookmark");

like.addEventListener("click", e=>{
    if(!loginCheck){
        alert("로그인 후 이용해주세요");
        return;
    }
    
    let check;

    if(e.target.firstElementChild.classList.contains("fa-regular")){
        check = 0;
    } else {
        check = 1;
    }

    const dataObj = {"recipeNo" : recipeNo, "check" : check};

    fetch("/recipe/like", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(dataObj)
    })
    .then(resp => resp.text())
    .then( count => {
        if(count == -1){
            alert("좋아요 처리 실패");
            return;
        }
        e.target.firstElementChild.classList.toggle("fa-regular");
        e.target.firstElementChild.classList.toggle("fa-solid");

    })
    .catch(e=>console.log(e));
});

bookmark.addEventListener("click", e=>{
    if(!loginCheck){
        alert("로그인 후 이용해주세요");
        return;
    }
    
    let check;

    if(e.target.firstElementChild.classList.contains("fa-regular")){
        check = 0;
    } else {
        check = 1;
    }

    const dataObj = {"recipeNo" : recipeNo, "check" : check};

    fetch("/recipe/bookmark", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(dataObj)
    })
    .then(resp => resp.text())
    .then( count => {
        if(count == -1){
            alert("좋아요 처리 실패");
            return;
        }
        e.target.firstElementChild.classList.toggle("fa-regular");
        e.target.firstElementChild.classList.toggle("fa-solid");

    })
    .catch(e=>console.log(e));
});