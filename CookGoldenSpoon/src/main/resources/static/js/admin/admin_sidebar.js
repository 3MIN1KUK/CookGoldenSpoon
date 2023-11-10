

  // 현재 페이지 URL을 가져오기
var currentURL = location.href;

let selectLink = '';


if(currentURL.includes('memberSearch')){
  selectLink = 'memberSearch';
}

const activeLink = document.getElementById(selectLink);
activeLink.style.background='#0d6efd';
activeLink.style.pointerEvents='none';
activeLink.style.userSelect='none';
