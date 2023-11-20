
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

/* x 버튼이 클릭 된 input순서 기록 */
const deleteCompleteOrderSet = new Set();
const deleteStepOrderSet = new Set();
const deleteThumbnailSet = new Set();

/* 사진 추가 버튼 백업용 */

const previewThumbnail = document.getElementsByClassName("preview");
const inputThumbnail = document.getElementsByClassName("inputImage1");
const deleteThumbnail = document.getElementsByClassName("delete-image");

const stepPreviewList = document.getElementsByClassName("stepPreview");
const stepInputImageList = document.getElementsByClassName("processImages");
const stepDeleteImageList = document.getElementsByClassName("stepDeleteBtn");

const completePreviewList = document.getElementsByClassName("completePreview");
const completeInputImageList = document.getElementsByClassName("completeImages");
const completeDeleteImageList = document.getElementsByClassName("completeDeleteBtn");

const backupThumbnailList = new Array(inputThumbnail.length);
let backupStepList = new Array(stepInputImageList.length);
const backupCompleteList = new Array(completeInputImageList.length);

// 썸네일 ---------------------------------------------------------------------------------
const changeThumbnailImageFn = (imageInput, order)=>{
  
  const maxSize = 1024*1024*10;
  
  const uploadFile = imageInput.files[0];
  
  // 파일 선택 취소
  if(uploadFile == undefined){
    
    const temp = backupThumbnailList[order].cloneNode(true);
    
    imageInput.after(temp);
    imageInput.remove();
    imageInput = temp;
    
    imageInput.addEventListener("change", ()=>{
      changeThumbnailImageFn(imageInput, order);
    });
    return;
  }
  
  // 크기 초과
  if(uploadFile.size > maxSize){
    alert("10MB 이하의 이미지를 선택해주세요");
    
    // 없다가 추가한 경우 빈칸으로 만들어 초기화
    if(backupThumbnailList[order] == undefined){
      imageInput.value = "";
    }
    
    // 있다가 추가한 경우
    else{
      const temp = backupThumbnailList[order].cloneNode(true);
      
      imageInput.after(temp);
      imageInput.remove();
      imageInput = temp;
      
      imageInput.addEventListener("change", ()=>{
        changeThumbnailImageFn(imageInput, order);
      });
    }
    return;
  }
  
  const reader = new FileReader();
  
  reader.readAsDataURL(uploadFile);
  
  reader.onload = e=>{
    const url = e.target.result;
    
    previewThumbnail[order].src = url;
    
    backupThumbnailList[order] = imageInput.cloneNode(true);
    deleteThumbnailSet.delete(order);
  }
};

// 썸네일
for(let i = 0; i<inputThumbnail.length; i++){
  
  // 이미지 선택 또는 취소 시
  inputThumbnail[i].addEventListener("change", e=>{
    changeThumbnailImageFn(e.target, i);
  });
  
  // x 버튼 클릭 시
  deleteThumbnail[i].addEventListener("click", ()=>{
    previewThumbnail[i].setAttribute("src", logo);
    
    inputThumbnail[i].value = "";
    
    backupThumbnailList[i] = undefined;
    deleteThumbnailSet.add(i);
  })
}
// ---------------------------------------------------------------------------------




// Step -----------------------------------------------------------------------------
const changeStepImageFn = (imageInput)=>{

  let order;
  
  for(let i=0; i<stepInputImageList.length ; i++){
    if(stepInputImageList[i] == imageInput){
      order = i;
    }
  }
  

  
  const maxSize = 1024*1024*10;
  
  const uploadFile = imageInput.files[0];
  
  // 파일 선택 취소
  if(uploadFile == undefined){
    
    const temp = backupStepList[order].cloneNode(true);
    
    imageInput.after(temp);
    imageInput.remove();
    imageInput = temp;
    
    imageInput.addEventListener("change", ()=>{
      changeStepImageFn(imageInput, order);
    });
    return;
  }
  
  // 크기 초과
  if(uploadFile.size > maxSize){
    alert("10MB 이하의 이미지를 선택해주세요");
    
    // 없다가 추가한 경우 빈칸으로 만들어 초기화
    if(backupStepList[order] == undefined){
      imageInput.value = "";
    }
    
    // 있다가 추가한 경우
    else{
      const temp = backupStepList[order].cloneNode(true);
      
      imageInput.after(temp);
      imageInput.remove();
      imageInput = temp;
      
      imageInput.addEventListener("change", ()=>{
        changeStepImageFn(imageInput, order);
      });
    }
    return;
  }
  
  const reader = new FileReader();
  
  reader.readAsDataURL(uploadFile);
  
  reader.onload = e=>{
    const url = e.target.result;
    
    stepPreviewList[order].src = url;
    
    backupStepList[order] = imageInput.cloneNode(true);
  }
};
// 과정사진
for(let i = 0; i<stepInputImageList.length; i++){
  
  // 이미지 선택 또는 취소 시
  stepInputImageList[i].addEventListener("change", e=>{
    changeStepImageFn(e.target);
  });

  // x 버튼 클릭 시
  stepDeleteImageList[i].addEventListener("click", ()=>{
    stepPreviewList[i].setAttribute("src", camera);

    stepInputImageList[i].value = "";

    backupStepList[i] = undefined;
  })
}
// ---------------------------------------------------------------------------------


// 완성 ---------------------------------------------------------------------------------
const changeCompleteImageFn = (imageInput, order)=>{

  const maxSize = 1024*1024*10;

  const uploadFile = imageInput.files[0];

  // 파일 선택 취소
  if(uploadFile == undefined){

    const temp = backupCompleteList[order].cloneNode(true);

    imageInput.after(temp);
    imageInput.remove();
    imageInput = temp;

    imageInput.addEventListener("change", ()=>{
      changeCompleteImageFn(imageInput, order);
    });
    return;
  }

  // 크기 초과
  if(uploadFile.size > maxSize){
    alert("10MB 이하의 이미지를 선택해주세요");

    // 없다가 추가한 경우 빈칸으로 만들어 초기화
    if(backupCompleteList[order] == undefined){
      imageInput.value = "";
    }

    // 있다가 추가한 경우
    else{
      const temp = backupInputList[order].cloneNode(true);

      imageInput.after(temp);
      imageInput.remove();
      imageInput = temp;

      imageInput.addEventListener("change", ()=>{
        changeCompleteImageFn(imageInput, order);
      });
    }
    return;
  }
  
  const reader = new FileReader();

  reader.readAsDataURL(uploadFile);

  reader.onload = e=>{
    const url = e.target.result;

    completePreviewList[order].src = url;

    backupCompleteList[order] = imageInput.cloneNode(true);

    deleteCompleteOrderSet.delete(order);
  }
};
// 완성사진
for(let i = 0; i<completeInputImageList.length; i++){
  
  // 이미지 선택 또는 취소 시
  completeInputImageList[i].addEventListener("change", e=>{
    changeCompleteImageFn(e.target, i);
  });

  // x 버튼 클릭 시
  completeDeleteImageList[i].addEventListener("click", ()=>{
    completePreviewList[i].setAttribute("src", camera);

    completeInputImageList[i].value = "";

    backupCompleteList[i] = undefined;

    deleteCompleteOrderSet.add(i);
  })
}
// ---------------------------------------------------------------------------------






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
  
  const textarea = backup.children[1].children[0].children[0];
  textarea.value = "";
  
  const inputFile = backup.children[1].children[1].children[1];
  inputFile.value = "";
  
  inputFile.previousElementSibling.src = camera;

  const preview = inputFile.previousElementSibling;
  
  const delStepBtn = backup.children[1].children[2];

  const xBtn = document.createElement("i");
  xBtn.classList.add("x-btn", "fa-solid", "fa-minus");
  backup.append(xBtn);
  
  parentElement.append(backup);

  backupStepList = new Array(stepInputImageList.length);

  xBtn.addEventListener("click", e=>{
    e.target.parentElement.remove();
    backupStepList = new Array(stepInputImageList.length);
    for(let i = 0; i<stepInputImageList.length; i++){
  
      backupStepList[i] = stepInputImageList[i].cloneNode(true);
      // 이미지 선택 또는 취소 시
    
      // x 버튼 클릭 시
      stepDeleteImageList[i].addEventListener("click", ()=>{
        stepPreviewList[i].setAttribute("src", camera);
    
        stepInputImageList[i].value = "";
    
        backupStepList[i] = undefined;
      })
    }
  })
  for(let i = 0; i<stepInputImageList.length; i++){
    backupStepList[i] = stepInputImageList[i].cloneNode(true);
  }
    // 이미지 선택 또는 취소 시
    inputFile.addEventListener("change", e=>{
      changeStepImageFn(e.target);
    });
  
    // x 버튼 클릭 시
    delStepBtn.addEventListener("click", ()=>{
      preview.setAttribute("src", camera);
  
      inputFile.value = "";
  
      let order;
  
      for(let i=0; i<stepInputImageList.length ; i++){
        if(stepInputImageList[i] == imageInput){
          order = i;
        }
      }


      backupStepList[order] = undefined;
    })
  
});

const cookProcess = document.getElementsByClassName("cookProcess");

for(let i = 1; i<cookProcess.length; i++){
  const xBtn = document.createElement("i");
  xBtn.classList.add("x-btn", "fa-solid", "fa-minus");
  xBtn.addEventListener("click",e=>{
    e.target.parentElement.remove();
  });
  cookProcess[i].append(xBtn);
}


//-------------------------------------------------------------------------

// 태그

function preventSubmit(event) {
  if (event.target.id == "inputTag" && event.key === "Enter") {
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

    const tags = document.querySelectorAll('input[name="recipeTagName"]');
    let maxLength = "";
    if(tags.length > 0){
      for(let tag of tags){
        maxLength += tag.value;
      }
      if(maxLength.length > 50){
        alert("더 이상 태그를 입력할 수 없습니다");
        e.preventDefault
        return;
      }
    }

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
const tagBtn = document.getElementsByClassName("tagBtn");
for(let tag of tagBtn){
  tag.addEventListener("click", e=>{
    e.target.parentElement.remove();
  });
}
// ----------------------------------------------------------------


// 재료 추가 버튼

const materialBtn = document.getElementById("materialBtn");

materialBtn.addEventListener("click", ()=>{
  const parent = document.querySelector(".mnameBox");
  
  const div = document.createElement("div");
  div.classList.add("material-detail");
  div.innerHTML = parent.children[0].innerHTML;
  div.children[0].value = "";
  div.children[1].value = "";
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
const materialDetail = document.getElementsByClassName("material-detail");

for(let i = 1; i<materialDetail.length; i++){
  const btn = document.createElement("button");
  btn.setAttribute("type", "button");
  btn.innerHTML = "&times;"
  btn.classList.add("delMaterialBtn");
  btn.addEventListener("click", e=>{
    e.target.parentElement.outerHTML = "";
  });
  materialDetail[i].append(btn);
}



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

  const stepImg = document.querySelectorAll("img.stepPreview");

  for(let i = 0; i<stepImg.length; i++){
    const input = document.createElement("input");

    input.setAttribute("name", "stepImg");
    input.setAttribute("type", "hidden");
    input.value = stepImg[i].src;

    recipeFrm.append(input);

  }


  document.querySelector("[name='deleteCompleteOrder']").value = Array.from(deleteCompleteOrderSet)
  document.querySelector("[name='deleteThumbnail']").value = Array.from(deleteThumbnailSet)

  document.querySelector("[name='querystring']").value = location.search;

});
const selectBox = document.getElementsByClassName("selectBox");
for(let i=0; i<selectBox.length; i++){
  const options = selectBox[i].children;
  for(let option of options){
    if(option.innerText == recipeSelectBox[i]){
      option.selected = true;
    }
  }
}

/* 전송 취소 버튼 */
const cancelBtn = document.getElementsByClassName("cancelBtn")[0];

cancelBtn.addEventListener("click", ()=>{
  window.history.back();
});





