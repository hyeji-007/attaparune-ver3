import{u as oe,c as l,d as le,j as e,L as de,H as ce,C as xe,V as pe,F as ue,T as me,m as n,Y as d,a2 as he,n as g,o as ge,W as fe,X as je,a1 as j,q,v as L,Z as be,a4 as f,_ as c}from"./index-QD4sUlUr.js";import{h as ye}from"./index.esm-BvlG2ZeS.js";import{u as we}from"./useModal-CUY2NTKe.js";const R=j.button`
  color: #fff;
  border-radius: 5px;
  width: 120px;
  padding: 15px 0;
  font-size: 20px;

  @media (max-width: 430px) {
    font-size: 14px;
    max-width: 80px;
    width: 100%;
    padding: 10px 0;
  }
  @media (max-width: 1400px) and (min-width: 431px) {
    width: 120px;
    padding: 15px 0;
    font-size: 20px;
  }
`,C=j.input`
  border-bottom: 1px solid #bababa;
  color: #bababa;
  margin-right: 30px;
  width: 350px;
  font-size: 24px;
  padding: 15px 0;
  @media (max-width: 430px) {
    margin-right: 20px;
    max-width: 220px;
    width: 100%;
    padding: 10px 0;
  }
  @media (max-width: 1400px) and (min-width: 431px) {
    margin-right: 30px;
    width: 350px;
    font-size: 24px;
    padding: 15px 0;
  }
`,v=j.div`
  padding: 10px 30px;
  border-radius: 30px;
  background-color: #6f4cdb;
  background-color: #ddd;
  color: #fff;
  cursor: pointer;
`,M=j.div`
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 16px;
  span {
    padding: 10px 25px;
    font-weight: 700;
    color: #6f4cdb;
  }
  input {
    font-size: 16px;
    background-color: none;
    width: 120px;
    height: 40px;
  }
`,Ce=be({restaurantName:c().required("가게이름은 필수입니다.").min(2,"가게이름은 최소 2자 이상이어야 합니다."),email:c().required("이메일은 필수입니다.").email("올바른 이메일 형식이 아닙니다."),restaurantAddress:c(),businessNumber:c(),restaurantNumber:c().required("전화번호는 필수입니다.").matches(/^\d{10,12}$/,"하이픈을 빼고 10~12자리 숫자만 입력해주세요."),categoryId:f(),operatingHours:c(),restaurantDescription:c().required("상세 설명은 필수입니다."),maxCapacity:f().min(1,"최대 수용 인원은 1 이상이어야 합니다."),lat:f(),lng:f()});function De(){var B,F,z,$,V,H;const k=oe(),[O,Y]=l.useState(!1),[N,K]=l.useState({}),[p,D]=l.useState({startTime:"10:00",endTime:"22:00"}),[S,b]=l.useState(!1),[U,P]=l.useState(!1),{Modal:W,open:y,close:X}=we({title:"주소검색"}),{register:r,handleSubmit:Z,formState:{errors:x},watch:a,setValue:i}=le({mode:"all",resolver:je(Ce)}),_=async s=>{try{await q.post("/api/admin/restaurant/v3/enrollment",s),L.fire({title:"가게 입점 신청이 완료 되었습니다.",icon:"success",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(t=>{t.isConfirmed&&k("/store")})}catch(t){console.log(t)}},G=s=>{S?(console.log(s),Y(t=>!t),_(s)):L.fire({title:"사업자 진위여부를 확인해주세요.",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1})},J=a("restaurantName"),Q=a("email"),w=a("restaurantAddress"),u=a("businessNumber"),ee=a("restaurantNumber"),m=a("categoryId"),se=a("operatingHours"),te=a("restaurantDescription"),ae=a("maxCapacity"),A=a("lat"),I=a("lng"),T=J&&Q&&w&&u&&ee&&m&&se&&te&&ae&&A&&I,re=async()=>{P(!0);try{const t=(await q.post(`/api/company/status?bNo=${u}`)).data.resultData;t.bstt==="계속사업자"||t.bstt==="신규사업자"?b(!0):b(!1)}catch(s){console.log(s)}},ne=async s=>{try{const o=await(await fetch(`https://dapi.kakao.com/v2/local/search/address.json?query=${encodeURIComponent(s)}`,{headers:{Authorization:"KakaoAK c39062d8559862a0fe376de133d3af0c"}})).json();if(o.documents.length>0){const{x:h,y:E}=o.documents[0];i("lat",E),i("lng",h),console.log("이 주소의 위도 : ",E,", 경도 : ",h)}}catch(t){console.error("Error fetching coordinates:",t)}},ie=async s=>{console.log(s);let t=s.address,o="";const h=s.zonecode;s.addressType==="R"&&(s.bname!==""&&(o+=s.bname),s.buildingName!==""&&(o+=o!==""?`, ${s.buildingName}`:s.buildingName),t+=o!==""?` (${o})`:""),await ne(t),X(),K({fullAddress:t,zoneCode:h}),i("restaurantAddress",t)};return l.useEffect(()=>{i("operatingHours",`${p.startTime} ~ ${p.endTime}`),console.log("운영시간 ",a("operatingHours"))},[p]),l.useEffect(()=>{i("categoryId",1)},[]),l.useEffect(()=>{console.log(w),console.log("이 주소의 위도 : ",A),console.log("이 주소의 경도 : ",I)},[w]),e.jsxs("div",{className:"bg-white h-[100vh] overflow-y-auto",children:[e.jsxs(de,{style:{position:"relative"},children:[e.jsx(ce,{children:e.jsx(xe,{children:e.jsx(pe,{className:"w-full h-full cursor-pointer",onClick:()=>k(-1)})})}),e.jsx(ue,{children:e.jsxs("form",{onSubmit:Z(G),children:[e.jsx("input",{type:"tel",className:"absolute left-[-5000px]",...r("lat")}),e.jsx("input",{type:"tel",className:"absolute left-[-5000px]",...r("lng")}),e.jsx(me,{children:"매장 입점 신청서"}),e.jsxs(n,{children:[e.jsxs("div",{className:"flex justify-between items-center w-[500px]",children:[e.jsx(C,{type:"text",placeholder:"사업자등록번호",...r("businessNumber")}),e.jsx(R,{type:"button",style:{backgroundColor:u?"#6F4CDB":"#ddd"},disabled:!u,onClick:()=>{re()},children:"번호조회"})]}),U?S?e.jsxs(d,{style:{color:"#888"},children:[e.jsx(he,{}),"입점신청 가능한 사업자번호입니다."]}):e.jsx(d,{style:{color:"red"},children:"사업자 번호를 확인해 주세요."}):e.jsx(e.Fragment,{})]}),e.jsxs(n,{children:[e.jsx(g,{type:"text",placeholder:"가게 이름",...r("restaurantName")}),e.jsx(d,{children:(B=x.restaurantName)==null?void 0:B.message})]}),e.jsxs(n,{children:[e.jsx(g,{type:"email",placeholder:"이메일",...r("email")}),e.jsx(d,{children:(F=x.email)==null?void 0:F.message})]}),e.jsxs(n,{children:[e.jsx(g,{type:"tel",maxLength:12,placeholder:"가게 전화번호",...r("restaurantNumber")}),e.jsx(d,{children:(z=x.restaurantNumber)==null?void 0:z.message})]}),e.jsxs(n,{children:[e.jsxs("div",{className:"flex justify-between items-center w-[500px]",children:[e.jsx(C,{type:"text",placeholder:"가게 주소",value:N?N.fullAddress:"",onClick:()=>y(),...r("restaurantAddress")}),e.jsx(R,{type:"button",style:{backgroundColor:"#6F4CDB"},onClick:()=>y(),children:"주소찾기"})]}),e.jsx(d,{children:($=x.restaurantAddress)==null?void 0:$.message})]}),e.jsxs(n,{children:[e.jsx(g,{type:"text",placeholder:"가게 상세설명",...r("restaurantDescription")}),e.jsx(d,{children:(V=x.restaurantDescription)==null?void 0:V.message})]}),e.jsxs(n,{children:[e.jsxs("div",{className:"flex items-center justify-between w-[500px] text-[24px]",children:[e.jsx("div",{children:"최대 수용 인원 : "}),e.jsx(C,{type:"number",placeholder:"0",style:{width:280,textAlign:"right",paddingRight:10},...r("maxCapacity",{setValueAs:s=>s===""?0:s})}),e.jsx("div",{children:"명"})]}),e.jsx(d,{children:(H=x.maxCapacity)==null?void 0:H.message})]}),e.jsx(n,{children:e.jsxs("div",{className:"flex justify-between items-center w-[500px] mt-[20px]",children:[e.jsx("div",{style:{fontSize:24},children:"카테고리 선택"}),e.jsxs("div",{style:{display:"flex",gap:20},children:[e.jsx(v,{onClick:()=>i("categoryId",1),style:{backgroundColor:m===1&&"#6F4CDB"},children:"한식"}),e.jsx(v,{onClick:()=>i("categoryId",2),style:{backgroundColor:m===2&&"#6F4CDB"},children:"중식"}),e.jsx(v,{onClick:()=>i("categoryId",3),style:{backgroundColor:m===3&&"#6F4CDB"},children:"일식"})]})]})}),e.jsxs(n,{children:[e.jsx("div",{className:"text-[24px] text-left",children:"운영시간"}),e.jsxs("div",{className:"flex items-center justify-between w-[500px] mt-[20px]",children:[e.jsxs(M,{children:[e.jsx("span",{children:"오픈시간"}),e.jsx("input",{type:"time",value:p.startTime,onChange:s=>D(t=>({...t,startTime:s.target.value}))})]}),e.jsxs(M,{children:[e.jsx("span",{children:"마감시간"}),e.jsx("input",{type:"time",value:p.endTime,onChange:s=>D(t=>({...t,endTime:s.target.value}))})]})]})]}),e.jsx("div",{className:"mx-[20px]",children:e.jsx(ge,{type:"submit",style:{backgroundColor:T?"#6F4CDB":"#ddd",marginBottom:100},disabled:!T,children:"확인"})})]})})]}),y?e.jsx(W,{children:e.jsx(ye,{onComplete:s=>ie(s)})}):e.jsx(e.Fragment,{}),O&&e.jsx(fe,{})]})}export{De as default};
