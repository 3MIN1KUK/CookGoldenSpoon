let reportNo;
const reportAnswerSubmit = document.getElementById('report-answer-submit');
var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
let getReportAnswer;


reportAnswerSubmit.addEventListener('click', () =>{
  
  const data = {};
  data.reportNo = reportNo;

  console.log(data);


  fetch("/admin/reportAnswer",{
    method : "POST",
    headers : {"Content-type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result =>{
    console.log(result);
    if(result > 0){

      myModal.hide();
      alert("신고 처리 완료")
      getReportAnswer.innerHTML = 'Y';
    } else{
      alert("신고 처리 실패")
    }
  })


})

function reportAnswer() {

  const reportAnswerValue = answerArea.innerHTML;

  const data = {};
  data.reportNo = reportNo;
  data.reportAnswer = reportAnswerValue;

  console.log(data);


  fetch("/admin/reportAnswer",{
    method : "POST",
    headers : {"Content-type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result =>{
    if(result > 0){
      alert("신고 처리 완료")

    } else{
      alert("신고 처리 실패")
    }
  })
}


function reportDetail(thisReportNo, thisReport) {

  reportNo = thisReportNo;

  fetch("/admin/reportDetail?reportNo=" + reportNo,{
    method : "GET",
    headers : {"Content-type" : "application/json"}
  })
  .then(res => res.json())
  .then(result =>{
    console.log(result);

    const reportTitle = document.getElementById("report-title");
    const reportContent = document.getElementById("report-content");
    const reportLocationBtn = document.getElementById("report-location");
    getReportAnswer = thisReport.parentElement.nextElementSibling;

    reportTitle.innerHTML = result.reportTitle;
    reportContent.innerHTML = result.reportContent;
    reportLocationBtn.setAttribute("onclick", `location.href='${result.reportLocation}'`);

  })

}