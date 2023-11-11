
/* 요리영상 추가 버튼 */
const processBtn1 = document.getElementsByClassName("processBtn1");

/* 요리과정 추가 버튼 - 완료 */
const processAddBtn4 = document.getElementsByClassName("processBtn4");

/* 요리과정사진 추가 버튼 */
const processPhotoBtn4 = document.getElementsByClassName("processPhotoBtn4")

/* 요리완성 사진 추가 버튼 */
const endProcessBtn8 = document.getElementsByClassName("endProcessBtn8")

/* 레시피 제출 버튼 */
const subBtn = document.getElementsByClassName("subBtn");

/* 레시피 미리보기 버튼 */
const prBtn = document.getElementsByClassName("prBtn");

/* 전송 취소 버튼 */
const cancelBtn = document.getElementsByClassName("cancelBtn");

/* 사진 추가 버튼 백업용 */


const backupInputList = new Array()











//-------------------------------------------------------------------------
/* 요리 과정 추가 버튼 */
const addBtn = document.getElementById("add");
const parentElement = document.getElementById("parentElement");
const container = document.querySelector(".container");
const backDiv = document.getElementsByClassName("backup")[0];
let backup;
/* 추가 버튼 클릭 시 */
addBtn.addEventListener("click", () => {
  backup = backDiv.cloneNode(true);

  console.log(backup);
  
  const textarea = backup.children[1].children[0].children[0];
  textarea.value = "";

  const inputFile = backup.children[1].children[1].children[0];
  inputFile.value = "";

  const xBtn = document.createElement("button");
  xBtn.type = "button";
  xBtn.classList.add("x-btn");
  xBtn.innerHTML = "&times;";

  backup.append(xBtn);

  parentElement.append(backup);

  xBtn.addEventListener("click", e=>{
    e.target.parentElement.remove();
  })
  
});



//-------------------------------------------------------------------------



function preventSubmit(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  }
}
document.getElementById("recipeFrm").addEventListener("keydown", preventSubmit);








const inputTag = document.getElementById("inputTag");
const tagnBox = document.querySelector(".tagnBox");
let tagName;
inputTag.addEventListener("keyup", e=>{
  if((e.target.value.trim().length == 0 && e.key == " ")
    || (e.target.value.trim().length == 0 && e.key == "Enter")){
      e.target.value = "";
      return;
  }


  if(e.key == "Enter"){

    tagName = e.target.value;

    e.target.value = "";

    const span = document.createElement("span");
    span.classList.add("tags");
    span.innerText = tagName;

    const button = document.createElement("button");
    button.setAttribute("type","button");
    button.classList.add("tagBtn");
    button.innerHTML = "&times;";

    const input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","recipeTagName");
    input.setAttribute("value", tagName);
    
    span.append(button, input);
    e.target.before(span);

    button.addEventListener("click", event=>{
      event.target.parentElement.remove();
    });
  }
});























