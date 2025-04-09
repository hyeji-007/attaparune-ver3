import{a1 as h,c as j,u as m,j as e,H as f,C as u,V as b,a2 as r}from"./index-QD4sUlUr.js";import{a as x}from"./index-2Luirxk7.js";import{u as k}from"./useModal-CUY2NTKe.js";const l=h.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  > * {
    cursor: pointer;
  }
  input {
    display: none;
  }
  em {
    display: inline-block;
    color: #ddd;
    transform: translateY(1.5px);
    margin-right: 8px;
  }
  input:checked + em {
    color: #ff0000;
  }
  b {
    color: #6f4cdb;
    font-size: 14px;
  }
  span {
    font-weight: 600;
  }
`,o=h.div`
  padding: 20px 30px 0;
  overflow-y: auto;
  text-align: left;
  max-width: 500px;
  line-height: 1.35em;
  margin-bottom: 10px;
  * {
    word-break: keep-all;
  }
  > div {
    padding: 5px 0;
    margin-bottom: 10px;

    p {
      font-weight: 600;
      line-height: 1.8em;
      margin-bottom: 5px;
    }
    span {
      display: block;
      padding: 5px 10px;
      font-size: 14px;
      color: #555;
      line-height: 1.5em;
    }
    > ul {
      font-size: 13px;
      padding: 10px 25px;
      &:last-of-type {
        margin-bottom: 0;
      }
      > li {
        color: #777;
        margin-bottom: 10px;
        > ul {
          background-color: #eee;
          margin-top: 10px;
          padding: 15px;
          line-height: 1.5em;
        }
      }
    }
  }
  .item {
    font-size: 14px;
    line-height: 2em;
    b {
      font-weight: 700;
      display: inline-block;
      width: 80px;
    }
    em {
      color: #777;
    }
  }
`,v=h.div`
  padding: 40px;
  margin: 0 auto;
  margin-top: 50px;
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 600px;
  border-radius: 10px;
  border: 0.5px solid #eee;
  box-shadow:
    0px 20px 25px -5px rgba(0, 0, 0, 0.1),
    0px 10px 10px -5px rgba(0, 0, 0, 0.04);
  button {
    padding: 10px 0;
    border-radius: 5px;
    background-color: #6f4cdb;
    color: #fff;
    margin-top: 150px;
  }
`;function B(){const[s,n]=j.useState({agree1:!1,agree2:!1,agree3:!1,agree4:!1}),[i,d]=j.useState({modal1:!1,modal2:!1,modal3:!1,modal4:!1}),p=m(),a=s.agree1&&s.agree2&&s.agree3&&s.agree4,g=()=>{if(i.modal1)return"이용약관";if(i.modal2)return"개인정보 처리방침";if(i.modal3)return"위치 정보";if(i.modal4)return"푸시 알림"},{Modal:c,open:t}=k({title:g()});return e.jsxs("div",{className:"text-center bg-white h-[100vh]",children:[e.jsx(f,{children:e.jsx(u,{children:e.jsx(b,{style:{width:"100%",height:"100%",cursor:"pointer"},onClick:()=>p(-1)})})}),e.jsxs("div",{style:{marginBottom:20,fontSize:28,fontWeight:700},children:[e.jsx("span",{style:{color:"#6f4cdb"},children:"[아따빠르네]"})," 이용약관 및 개인정보 처리방침"]}),e.jsxs(v,{children:[e.jsxs(l,{children:[e.jsx("input",{type:"checkbox",checked:a}),e.jsx("em",{children:e.jsx(r,{onClick:()=>{n(a?{agree1:!1,agree2:!1,agree3:!1,agree4:!1}:{agree1:!0,agree2:!0,agree3:!0,agree4:!0})}})}),e.jsx("span",{children:"전체동의"})]}),e.jsx("div",{style:{border:"1px solid #eee",marginBottom:20,width:"100%"}}),e.jsxs(l,{children:[e.jsx("input",{type:"checkbox",checked:s.agree1}),e.jsx("em",{children:e.jsx(r,{onClick:()=>n({...s,agree1:!s.agree1})})}),e.jsxs("span",{children:[e.jsx("b",{children:"(필수)"})," 이용약관에 동의합니다.",e.jsx("em",{children:e.jsx(x,{onClick:()=>{t(),d({modal1:!0})},style:{marginBottom:2,width:12,height:12,color:"#333",marginLeft:5}})})]})]}),e.jsxs(l,{children:[e.jsx("input",{type:"checkbox",checked:s.agree2}),e.jsx("em",{children:e.jsx(r,{onClick:()=>n({...s,agree2:!s.agree2})})}),e.jsxs("span",{children:[e.jsx("b",{children:"(필수)"})," 개인정보 처리방침에 동의합니다.",e.jsx("em",{children:e.jsx(x,{onClick:()=>{t(),d({modal2:!0})},style:{marginBottom:2,width:12,height:12,color:"#333",marginLeft:5}})})]})]}),e.jsxs(l,{children:[e.jsx("input",{type:"checkbox",checked:s.agree3}),e.jsx("em",{children:e.jsx(r,{onClick:()=>n({...s,agree3:!s.agree3})})}),e.jsxs("span",{children:[e.jsx("b",{children:"(필수)"})," 위치 정보 제공에 동의합니다.",e.jsx("em",{children:e.jsx(x,{onClick:()=>{t(),d({modal3:!0})},style:{marginBottom:2,width:12,height:12,color:"#333",marginLeft:5}})})]})]}),e.jsxs(l,{children:[e.jsx("input",{type:"checkbox",checked:s.agree4}),e.jsx("em",{children:e.jsx(r,{onClick:()=>n({...s,agree4:!s.agree4})})}),e.jsxs("span",{children:[e.jsx("b",{children:"(필수)"})," 푸시 알림에 동의합니다.",e.jsx("em",{children:e.jsx(x,{onClick:()=>{t(),d({modal4:!0})},style:{marginBottom:2,width:12,height:12,color:"#333",marginLeft:5}})})]})]}),e.jsx("button",{style:{backgroundColor:!a&&"#ddd"},disabled:!a,onClick:()=>p("/auth/signup"),children:"다음"})]}),i.modal1&&e.jsx(c,{children:e.jsxs(o,{children:[e.jsxs("div",{children:[e.jsx("p",{children:"제1조 (목적)"}),e.jsx("span",{children:'이 약관은 [아따빠르네] (이하 "서비스")가 제공하는 음식점 예약 및 회사 식대 관리 서비스의 이용에 대한 규정과 조건을 정하는 것을 목적으로 합니다.'})]}),e.jsxs("div",{children:[e.jsx("p",{children:"제2조 (서비스 이용)"}),e.jsx("span",{children:"1. 서비스는 사용자에게 음식점 예약, 대기 시간 알림, 그리고 회사의 식대 관리 기능을 제공합니다."}),e.jsx("span",{children:"2. 서비스 이용자는 서비스 내에서 제공되는 모든 기능을 본 약관과 법령에 따라 사용해야 합니다."})]}),e.jsxs("div",{children:[e.jsx("p",{children:"제3조 (회원 가입 및 계정 관리)"}),e.jsx("span",{children:"1. 서비스 이용자는 회원 가입 시 정확한 정보를 제공해야 하며, 이를 최신 상태로 유지해야 합니다."}),e.jsx("span",{children:"2. 회원은 자신의 계정을 안전하게 관리해야 하며, 제3자에게 계정을 공유하거나 양도할 수 없습니다."})]}),e.jsxs("div",{children:[e.jsx("p",{children:"제4조 (음식점 예약 서비스)"}),e.jsx("span",{children:"1. 사용자는 앱을 통해 원하는 음식점의 예약을 진행할 수 있습니다."}),e.jsx("span",{children:"2. 예약 시 정확한 정보(예약 시간, 인원 등)를 제공해야 하며, 예약 후 취소 시에는 해당 음식점의 정책에 따라 처리가 될 수 있습니다."}),e.jsx("span",{children:"3. 음식점 예약 및 대기 시간이 변동될 수 있으며, 사용자는 이에 동의한 상태에서 서비스를 이용합니다."})]}),e.jsxs("div",{children:[e.jsx("p",{children:"제5조 (회사 식대 관리)"}),e.jsx("span",{children:"1. 회사는 이 앱을 통해 직원들의 식대를 관리할 수 있으며, 관련된 정보(식사 내역, 금액 등)를 기록할 수 있습니다."}),e.jsx("span",{children:"2. 사용자는 회사가 제공하는 식대 관리 기능을 통해 본인의 식사 내역을 확인하고 관리할 수 있습니다."}),e.jsx("span",{children:"3. 회사 식대 관련 데이터는 회사 내부 정책에 따라 관리되며, 사용자는 이에 동의한 상태에서 서비스를 이용합니다."})]}),e.jsxs("div",{children:[e.jsx("p",{children:"제6조 (책임의 한계)"}),e.jsx("span",{children:"1. 서비스 제공자는 서버 오류나 네트워크 문제로 인해 서비스 이용에 불편이 발생할 수 있으며, 이에 대한 책임을 지지 않습니다."}),e.jsx("span",{children:"2. 사용자는 예약 정보나 식대 관련 내역에 오류가 발생했을 경우, 해당 문제를 음식점이나 회사와 직접 해결해야 합니다."})]})]})}),i.modal2&&e.jsx(c,{children:e.jsxs(o,{children:[e.jsxs("div",{children:[e.jsx("p",{children:"1. 개인정보의 수집 항목"}),e.jsx("span",{children:"서비스는 다음과 같은 개인정보를 수집합니다:"}),e.jsx("span",{children:"- 필수 항목: 이름, 전화번호, 이메일, 예약 내역, 식사 내역, 회사 관련 정보(식대 관리), 위치 정보 (위치 기반 서비스 사용 시)"})]}),e.jsxs("div",{children:[e.jsx("p",{children:"2. 개인정보의 이용 목적"}),e.jsx("span",{children:"수집된 개인정보는 다음의 목적을 위해 사용됩니다:"}),e.jsx("span",{children:"- 음식점 예약 서비스 제공 (예약 정보, 대기 시간 알림 등)"}),e.jsx("span",{children:"- 회사 식대 관리 (직원 식대 내역 관리)"}),e.jsx("span",{children:"- 회원 관리 및 서비스 개선"})]}),e.jsxs("div",{children:[e.jsx("p",{children:"3. 개인정보의 보유 기간"}),e.jsx("span",{children:"회원 탈퇴 시까지 개인정보는 보유됩니다. 탈퇴 후에는 해당 정보가 즉시 삭제됩니다."})]}),e.jsxs("div",{children:[e.jsx("p",{children:"4. 개인정보의 제3자 제공"}),e.jsx("span",{children:"서비스는 개인정보를 제3자에게 제공하지 않으며, 서비스 제공에 필요한 범위 내에서만 사용됩니다."})]})]})}),i.modal3&&e.jsx(c,{children:e.jsx(o,{children:e.jsxs("div",{children:[e.jsx("p",{children:"위치 정보 제공 동의"}),e.jsx("span",{children:"서비스는 위치 기반 서비스를 제공하기 위해 사용자의 위치 정보를 수집하고, 이를 통해 근처 음식점 예약가능 여부 예측 등을 제공합니다."}),e.jsx("span",{children:"위치 정보 제공에 동의하시면 서비스를 원활하게 이용할 수 있습니다."})]})})}),i.modal4&&e.jsx(c,{children:e.jsx(o,{children:e.jsxs("div",{children:[e.jsx("p",{children:"푸시 알림 동의"}),e.jsx("span",{children:"서비스는 예약 확인, 대기 시간 알림, 식대 내역 업데이트 등의 알림을 푸시 알림을 통해 제공합니다."}),e.jsx("span",{children:"푸시 알림을 수신하려면 동의가 필요합니다. 푸시 알림 수신 여부는 설정에서 언제든지 변경할 수 있습니다."})]})})})]})}export{o as AgreementDocumentDiv,l as CheckBoxDiv,B as default};
