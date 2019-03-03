# VulnWeb
進行網站安全攻防使用範例

<b>請勿將此範例網站部署在公開環境，否則被打穿概不負責</b> <br/>

該網站包含弱點如下所示 : <br/>
<ol>
  <li>SQL Injeciton</li>
  <li>持續型、DOM XSS</li>
  <li>CSRF</li>
  <li>伺服器端未驗證參數</li>
  <li>未正確實作登出機制</li>
  <li>使用可竄改外部參數作為認證資訊</li>
  <li>登入成功後將使用者帳號、密碼儲存在cookie做為日後自動登入用，並且沒做安全防禦設定</li>
  <li>LOG資料可以注入%0A做資料竄改</li>
</ol>
