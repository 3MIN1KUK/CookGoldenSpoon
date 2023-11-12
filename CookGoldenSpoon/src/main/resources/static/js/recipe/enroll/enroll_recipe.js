
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

// 태그

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

// ----------------------------------------------------------------


// 재료 추가 버튼

const materialBtn = document.getElementById("materialBtn");

materialBtn.addEventListener("click", ()=>{
  const parent = document.querySelector(".mnameBox");
  
  const div = document.createElement("div");
  div.classList.add("material-detail");
  div.innerHTML = parent.children[0].innerHTML;
  parent.append(div);
  const btn = document.createElement("button");
  btn.setAttribute("type", "button");
  btn.innerHTML = "&times;"
  btn.classList.add("delMaterialBtn");
  btn.addEventListener("click", e=>{
    e.target.parentElement.outerHTML = "";
  });
  div.append(btn);
});

// 유효성 검사
// 재료 수량 입력 시 둘 다 입력받기

const recipeFrm = document.getElementById("recipeFrm");

recipeFrm.addEventListener("submit", e=>{
  
  const recipeTitle = document.getElementById("recipeTitle");
  const recipeIntro = document.getElementById("recipeIntro");
  
  if(recipeTitle.value.trim().length == 0){
    alert("요리 이름을 적어주세요");
    recipeTitle.value = "";
    recipeTitle.focus();
    e.preventDefault();
    return;
  }
  if(recipeIntro.value.trim().length == 0){
    alert("한 줄 소개를 적어주세요");
    recipeIntro.value = "";
    recipeIntro.focus();
    e.preventDefault();
    return;
  }
  
  const materialName = document.querySelectorAll('input[name="materialName"]');
  const recipeMaterialQuantity = document.querySelectorAll('input[name="recipeMaterialQuantity"]');
  let materialCheck = true;

  for(let i = 0; i<materialName.length; i++){
    if(materialName[i].value.trim().length != 0){
      materialCheck = false;
    }
    if(!((materialName[i].value.trim().length != 0 && recipeMaterialQuantity[i].value.trim().length != 0)||
    (materialName[i].value.trim().length == 0 && recipeMaterialQuantity[i].value.trim().length == 0))){
        alert("재료명과 수량은 하나만 입력할 수 없습니다");
        materialName[i].focus();
        e.preventDefault();
        return;
    }else{
      materialName[i].value = materialName[i].value.trim();
      recipeMaterialQuantity[i].value = recipeMaterialQuantity[i].value.trim();
    }
  }

  if(materialCheck){
    alert("재료를 하나라도 적어주세요");
    materialName[0].value = "";
    materialName[0].focus();
    e.preventDefault();
    return;
  }

  const recipeStepContent = document.querySelectorAll('textarea[name="recipeStepContent"]');
  for(let i=0; i<recipeStepContent.length; i++){
    if(recipeStepContent[i].value.trim().length == 0){
      alert("요리과정을 적어주세요");
      recipeStepContent[i].value = "";
      recipeStepContent[i].focus();
      e.preventDefault();
      return;
    }
  }

  const recipeTip = document.getElementById("recipeTip");

  if(recipeTip.value.trim().length == 0){
    alert("나만의 팁을 적어주세요");
    recipeTip.value = "";
    recipeTip.focus();
    e.preventDefault();
    return;
  }

  const recipeTagName = document.querySelector('input[name="recipeTagName"]');
  const inputTag = document.getElementById("inputTag");
  if(recipeTagName == null){
    alert("태그를 하나 이상 적어주세요");
    inputTag.value = "";
    inputTag.focus();
    e.preventDefault();
    return;
  }





});














