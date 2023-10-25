const menu = document.querySelectorAll('.menu');
const liContainer = document.querySelectorAll('.li-container');
const header = document.querySelector('.header');


/* 네비게이션 */
for(let i = 0 ; i < menu.length ; i++){
     menu[i].addEventListener('mouseenter', ()=>{

          for(let u = 0; u < liContainer.length ; u++){
               liContainer[u].classList.add("dpnone");
          }
          header.setAttribute("style", 'height: 28rem;')
          header.addEventListener('mouseleave', ()=>{
               for(let u = 0; u < liContainer.length ; u++){
                    liContainer[u].classList.remove("dpnone");
               }
               header.removeAttribute("style");
         });
    })
}
