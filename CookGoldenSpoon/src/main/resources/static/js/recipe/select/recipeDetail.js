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

    const dataObj = {"recipeNo" : recipeNo, "check" : check, "starsCheck" : starsCheck};

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

const stars = document.getElementsByClassName("stars");

for(let i = 0; i<stars.length ; i++){
    stars[i].addEventListener("click", e=>{
        console.log(e.target.firstElementChild.value);

        if(!loginCheck){
            alert("로그인 후 이용해주세요");
            return;
        }
        
        const dataObj = {"recipeNo" : recipeNo, "starsCheck" : e.target.firstElementChild.value};

        fetch("/recipe/stars",{
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(dataObj)
        })
        .then(resp => resp.text())
        .then()
        .catch(e=>console.log(e));


    });
}