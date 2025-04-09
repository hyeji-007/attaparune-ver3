import{c,R as M,bn as P,u as T,aw as A,j as e,au as _,P as y,V as E,av as O,N as G,bo as K,I as q,v as x,q as H,a1 as r,Q}from"./index-QD4sUlUr.js";import{B as k}from"./index-DhYuA9Fd.js";import{F as U}from"./index-2Luirxk7.js";const V=r.div`
  background-color: #fff;
  width: 24px;
  height: 24px;
  border-radius: 12px;
  padding: 3px;
  cursor: pointer;
  position: absolute;
  top: 0;
  margin: 10px 20px;
  color: #333;
`,J=r.div`
  padding: 15px 25px;
  display: flex;
  justify-content: space-between;
  div > div {
    font-size: 10px;
    color: #bababa;
    margin-bottom: 5px;
  }
  span {
    color: #eee;
    padding: 0 5px;
  }
  h1 {
    margin-bottom: 5px;
    font-size: 1.25rem;
  }
  h2 {
    display: flex;
    align-items: center;
    gap: 5px;
    font-size: 10px;
  }
`,C=r.div`
  width: 100%;
  height: 10px;
  background-color: #ddd;
`,W=r.div`
  overflow-y: auto;
  padding: 15px 25px;
  padding-bottom: 100px;
  div {
    color: #707070;
  }
  span {
    word-wrap: break-word;
    font-size: 12px;
    font-weight: 700;
  }
  h1 {
    font-weight: 700;
    margin-bottom: 20px;
  }
`,X=r.div`
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 10px;
  img {
    width: 75px;
    height: 75px;
    border-radius: 5px;
  }
`,Y=r.div`
  display: flex;
  gap: 35px;
  justify-content: center;
  align-items: center;
  height: 70px;
  width: 100%;
  bottom: 0;
  position: absolute;
  background-color: #fff;
  box-shadow:
    rgba(0, 0, 0, 0.16) 0px 10px 36px 0px,
    rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;
  button {
    padding: 5px 40px;
    font-size: 14px;
    background-color: #6f4cdb;
    color: #fff;
    border-radius: 5px;
  }
`,Z=r.div`
  width: 20px;
  height: 20px;
  background-color: #ddd;
  color: #fff;
  border-radius: 50%;
  line-height: 20px;
  text-align: center;
  padding-right: 1px;
  font-size: 9px;
`,p=r.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 10px;
  margin-bottom: 10px;
  span {
    width: 40px;
    margin-right: 10px;
  }
  p {
    padding: 2px 5px;
    border-radius: 2px;
    background-color: #e7e1f9;
    color: #fff;
    font-size: 8px;
  }
`,D=r.div`
  position: absolute;
  width: 100%;
  bottom: 0;
  background-color: #fff;
  padding: 10px 0;
  border-radius: 5px 5px 0 0;
  box-shadow:
    rgba(0, 0, 0, 0.16) 0px 10px 36px 0px,
    rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;
`,ee=r.div`
  display: flex;
  justify-content: center;
  button {
    font-size: 10px;
    padding: 5px 100px;
    border-radius: 5px;
    background-color: #6f4cdb;
    color: #fff;
  }
`;function re(){var b,v,m,w;const[t,N]=c.useState({}),[u,h]=c.useState(!1),[te,g]=M(P),[o,l]=c.useState({}),[I,S]=c.useState([]),[f,B]=c.useState(!0),a=T(),{id:d}=A(),F=async()=>{try{const i=(await H.get(`/api/restaurant?restaurantId=${d}`)).data.resultData;S(i.menuCateList),N(i)}catch(n){console.log(n)}finally{setTimeout(()=>B(!1),1e3)}},z=()=>{switch(t.categoryId){case 1:return"한식";case 2:return"중식";case 3:return"일식";default:return"잘못된 값"}},j=n=>{const i=Q();n?i?(g(!0),h(!0)):x.fire({title:"로그인이 필요한 서비스입니다.",text:"확인을 누르면 로그인으로 이동합니다.",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(s=>{s.isConfirmed&&a("/auth")}):i?(g(!1),a(`/user/restaurant/detail/reserve/${d}`)):x.fire({title:"로그인이 필요한 서비스입니다.",text:"확인을 누르면 로그인으로 이동합니다.",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(s=>{s.isConfirmed&&a("/auth")})},R=[1,2,3,4,5,6,7,8],L=["11:30","12:00","12:30","13:00","13:30"];return c.useEffect(()=>{F(),console.log(d)},[]),e.jsxs("div",{style:{height:"100vh",backgroundColor:"white"},children:[f&&e.jsx("div",{style:{position:"absolute",top:0,left:0,width:"100%",height:"100%",backgroundColor:"white",zIndex:50},children:e.jsx(_,{message:"가게 정보 불러오는 중..."})}),!f&&e.jsxs(e.Fragment,{children:[(b=t==null?void 0:t.restaurantPics)!=null&&b.filePath?e.jsx("img",{src:`${y}/pic/restaurant/${t==null?void 0:t.restaurantId}/${(v=t==null?void 0:t.restaurantPics)==null?void 0:v.filePath}`,alt:"가게 이미지",style:{width:"100%",height:300,position:"relative",objectFit:"cover"}}):e.jsx("img",{src:"/restaurant_default.png",className:"object-cover bg-gray py-6"}),e.jsx(V,{children:e.jsx(E,{style:{width:"100%",height:"100%"},onClick:()=>a(-1)})}),e.jsx(J,{children:e.jsxs("div",{className:"w-full",children:[e.jsxs("div",{children:[(m=t==null?void 0:t.restaurantAddress)==null?void 0:m.match(/^(?:대구광역시|대구)\s*(.+)/)[1]," ",e.jsx("span",{children:"I"})," ",z()]}),e.jsxs("section",{className:"flex w-full justify-between items-center pr-16",children:[e.jsx("h1",{children:t==null?void 0:t.restaurantName}),e.jsxs("div",{className:"flex w-1/3 h-full items-center gap-2",children:[e.jsxs("div",{className:"flex items-center gap-1 ",children:[e.jsx(U,{className:"text-yellow text-lg drop-shadow-sm mb-0.5"}),e.jsx("p",{className:"tracking-wide text-black text-base",children:(w=t==null?void 0:t.ratingAvg)==null?void 0:w.toFixed(1)})]}),e.jsx("p",{className:"text-3xl text-darkGray pb-1.5 ",children:"·"}),e.jsxs("div",{className:"flex text-nowrap text-base gap-1.5 items-center cursor-pointer",onClick:()=>a(`/user/restaurant/detail/review/${t.restaurantId}`,{state:{reviewCnt:t==null?void 0:t.reviewCnt}}),children:[e.jsx("p",{className:"text-black text-base",children:"리뷰"}),e.jsxs("p",{className:"text-black text-base",children:[t==null?void 0:t.reviewCnt," 개"]}),e.jsx(O,{className:"text-black text-base"})]})]})]}),e.jsx("div",{dangerouslySetInnerHTML:{__html:G.sanitize(String(t==null?void 0:t.restaurantDescription))}}),e.jsxs("h2",{children:[e.jsx(K,{}),t==null?void 0:t.restaurantAddress]}),e.jsxs("h2",{style:{marginTop:10},children:[e.jsx(k,{}),"매장 연락처 : ",t==null?void 0:t.restaurantNumber]})]})}),e.jsx(C,{}),e.jsxs(W,{children:[e.jsx("h1",{children:"메뉴"}),I.map((n,i)=>e.jsx("div",{children:n.menuList.map((s,$)=>e.jsxs("div",{children:[e.jsxs(X,{children:[e.jsx("img",{src:`${y}/pic/menu/${s==null?void 0:s.menuId}/${s==null?void 0:s.menuPic}`,alt:"메뉴 이미지"}),e.jsxs("div",{children:[e.jsxs("div",{children:[e.jsx("div",{children:s==null?void 0:s.menuName}),e.jsxs("span",{children:[s==null?void 0:s.price.toLocaleString("ko-KR"),"원"]})]}),e.jsx("span",{className:"text-darkGray text-opacity-60",children:s==null?void 0:s.details})]})]}),e.jsx(C,{style:{height:1,backgroundColor:"#eee",marginBottom:10}})]},$))},i))]}),!u&&e.jsxs(Y,{children:[e.jsx("button",{onClick:()=>j(!1),children:"앉아서 주문"}),e.jsx("button",{onClick:()=>j(!0),children:"예약하기"})]}),u&&e.jsxs(D,{children:[e.jsx("div",{style:{display:"flex",justifyContent:"flex-end",marginRight:10},children:e.jsx(q,{onClick:()=>{h(!1),l({})}})}),e.jsx("div",{style:{textAlign:"center",fontSize:12,marginBottom:10},children:"예약할 시간과 인원을 선택해주세요"}),e.jsxs(p,{children:[e.jsx("span",{children:"인원수"}),e.jsx("div",{style:{display:"flex",gap:10},children:R.map((n,i)=>e.jsxs(Z,{style:{backgroundColor:n===o.count&&"#6F4CDB"},onClick:()=>l({...o,count:n}),children:[n,"명"]},i))})]}),e.jsxs(p,{children:[e.jsx("span",{children:"시간 선택"}),e.jsx("div",{style:{display:"flex",gap:5},children:L.map((n,i)=>e.jsxs("p",{style:{backgroundColor:n===o.time&&"#6F4CDB"},onClick:()=>l({...o,time:n}),children:[n==="11:30"?"오전":"오후"," ",n==="13:00"?"1:00":n==="13:30"?"1:30":n]},i))})]}),e.jsxs(p,{style:{gap:5,color:"#FF9500"},children:[e.jsx(k,{}),e.jsx("div",{children:"9명 이상은 가게에 직접 문의해주세요"})]}),e.jsx(ee,{children:e.jsx("button",{onClick:()=>{o.time&&o.count?a(`/user/restaurant/detail/reserve/${d}`,{state:o}):x.fire({title:"시간과 인원을 선택해주세요.",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1})},children:"확인"})})]})]})]})}export{re as default};
