const answerArea = document.getElementById('message-text');
let reportNo;
const reportAnswerSubmit = document.getElementById('report-answer-submit');
reportAnswerSubmit.addEventListener('click', () =>{
  
  const reportAnswerSubmit = answerArea.innerHTML;

  const data = {};
  data.reportNo = reportNo;
  data.reportAnswer = reportAnswerSubmit;

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
      alert("신고 처리 완료")
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
    console.log(result);
    if(result > 0){
      alert("신고 처리 완료")
    } else{
      alert("신고 처리 실패")
    }
  })
}


function reportDetail(thisReportNo, thisReport) {

  reportNo = thisReportNo;

  answerArea.innerHTML = "";
  fetch("/admin/reportDetail?reportNo=" + reportNo,{
    method : "GET",
    headers : {"Content-type" : "application/json"}
  })
  .then(res => res.json())
  .then(result =>{

    const reportTitle = document.getElementById("report-title");
    const reportContent = document.getElementById("report-content");

    reportTitle.innerHTML = result.reportTitle;
    reportContent.innerHTML = result.reportContent;

  })

}