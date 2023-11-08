// img 태그 5개
const previewList = document.getElementById("preview");
// input type="file" 태그 5개
const inputImgList = document.getElementById("inputImg");
// x버튼 5개
const deleteImgList = document.getElementById("delete-img");

// inputImgList 크기 만큼 백업용 배열을 생성
const backupIputList = new Array(inputImgList.length);

/* 이미지 선택 시 수행할 함수 */
const changeImgFn = (imgInput, order) => {

   // 파일 최대 크기(10MB)
   const maxSize = 1024 * 1024 * 10;

   // 업로드한 파일 정보가 담긴 객체
   const uploadFile = imgInput.files[0];

   // ----- 파일을 한 번 선택 한 후 최소 했을 때 -----
   if(uploadFile == undefined){
      console.log("파일 선택이 취소됨");
      // 1) backup한 order 번째 요소를 복제
      const temp = backupIputList[order].cloneNode(true);
      // 2) 화면에 원본 input을 temp로 바꾸기
      imgInput.after(temp); // 원본 다음에 temp 추가
      imgInput.remove(); // 원본을 화면에서 제거
      imgInput = temp; // temp를 imgInput 변수에 대입
      // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
      imgInput.addEventListener("change", () => {
         changeImgFn(imgInput, order);
      });
      return;
   }

   // ----- 선택된 파일의 크기가 지정된 크기를 초과하는 경우 -----
   if(uploadFile.size > maxSize) {
      alert("10MB 이하의 이미지를 선택 해주세요");

      // 이미지가 없다가 -> 추가된 경우
      if(backupIputList[order] == undefined){
         imgInput.value = '';
      }
      // 이미지가 있는 상태에서 새 이미지 선택
      else{
         // 1. backup한 order번째 요소를 복제
         const temp = backupIputList[order]. cloneNode(true);
         imgInput.after(temp);
         imgInput.remove();
         imgInput = temp;
         imgInput.addEventListener("change", () => {
            changeImgFn(imgInput, order);
         });
      }
      return;
   }
      
   // ----- 선택된 이미지 파일을 읽어와 미리 보기 만들기 -----
   const reader = new FileReader();
   reader.readAsDataURL(uploadFile);
   // 파일을 다 읽은 경우
   reader.onload = e => {
   const url = e.target.result; // 이미지가 변환된 dataUrl
   // order 번째 .preview에 이미지 추가
   previewList[order].src = url;
   // 파일이 업로드된 input 태그를 복제해서 backupInputList에 추가
   backupIputList[order] = imgInput.cloneNode(true);
   };
};

for(let i=0 ; i<inputImgList.length ; i++) {
   /* 이미지 선택 또는 취소 시 */
   inputImgList[i].addEventListener("change", e => {
      changeImageFn(e.target,    i);
      //          inputImage,    order
   });
   /* X버튼 클릭 시 */
   deleteImgList[i].addEventListener('click', () => {
      // 미리보기 삭제
      previewList[i].removeAttribute("src"); // src 속성 제거
      // input 태그 파일 제거
      inputImgList[i].value = '';
      // 같은 위치 backup 요소 제거
      backupInputList[i] = undefined;
   });

}


// ----------------------------------------------------------------------
/* 제출 시 유효성 검사 */
const boardWriteFrm = document.getElementById("boardWriteFrm");
boardWriteFrm.addEventListener("submit", e => {
   const title = document.querySelector("[name='boardTitle']");
   const content = document.querySelector("[name='boardContent']");
   // 제목 미입력
   if(title.value.trim().length == 0) {
      alert("제목을 입력해주세요");
      e.preventDefault(); // form 제출 X
      title.value = "";
      title.focus();
      return;
   }

   // 내용 미입력
   if(content.value.trim().length == 0) {
      alert("내용을 입력해주세요");
      e.preventDefault(); // form 제출 X
      content.value = "";
      content.focus();
      return;
   }
});