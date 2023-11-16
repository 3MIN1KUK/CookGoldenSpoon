

  // 현재 페이지 URL을 가져오기
var currentURL = window.location.pathname;

let selectLink = '';

if(currentURL.includes('memberSearch')){
  selectLink = 'memberSearch';
}

if(currentURL.includes('reportManagement')){
  selectLink = 'reportManagement';
}

if(currentURL.includes('recipeSearch')){
  selectLink = 'recipeSearch';
}

if(currentURL.includes('boardSearch')){
  selectLink = 'boardSearch';
}

if(currentURL.includes('commentSearch')){
  selectLink = 'commentSearch';
}



const activeLink = document.getElementById(selectLink);
activeLink.style.background='#0d6efd';
activeLink.style.pointerEvents='none';
activeLink.style.userSelect='none';
