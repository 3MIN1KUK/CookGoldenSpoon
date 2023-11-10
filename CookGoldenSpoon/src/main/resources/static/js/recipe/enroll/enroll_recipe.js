
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

const inputTag = document.getElementById("inputTag");
inputTag.addEventListener("keyup", e=>{
  if(e.key == "Enter"){
    let tag = e.target.value;
    const span = document.createElement("span");
    span.innerText = tag;
    span.classList.add("tags");
    inputTag.append(span);
  }
});



//-------------------------------------------------------------------------



























