
function reportAnswer(reportNo, reportAnswer) {

  const data = {};
  data.reportNo = reportNo;
  data.reportAnswer = reportAnswer;

  const answerArea = document.getElementById('message-text');
  answerArea.innerHTML = "";

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


function reportDetail(reportNo, thisReport) {
  fetch("/admin/reportDetail?reportNo=" + reportNo,{
    method : "GET",
    headers : {"Content-type" : "application/json"}
  })
  .then(res => res.json())
  .then(result =>{

    const reportTitle = document.getElementById("report-title");
    const reportContent = document.getElementById("report-content");

    reportTitle.innerHTML = result.reportTitle;
    reportContent.inert.innerHTML = result.reportContent;

  })

}