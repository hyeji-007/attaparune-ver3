import{u as q,e as T,bn as H,ax as Q,c as i,R as V,b9 as G,a as J,aw as W,ag as X,j as e,P as F,V as Z,N as ee,bo as te,a2 as se,bt as ne,bu as oe,bv as re,q as b,v as j,a1 as c,Q as ae,bw as B}from"./index-QD4sUlUr.js";import{B as ie}from"./index-DhYuA9Fd.js";import{b as ce,c as de}from"./index-CW4i2JGu.js";const ue=c.div`
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
`,le=c.div`
  padding: 15px 25px;
  div {
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
  }
  h2 {
    display: flex;
    align-items: center;
    gap: 5px;
    font-size: 10px;
  }
`,w=c.div`
  height: 10px;
  background-color: #ddd;
`,pe=c.div`
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
`,xe=c.div`
  position: relative;
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 10px;
  img {
    width: 75px;
    height: 75px;
    border-radius: 5px;
  }
`,L=c.div`
  display: flex;
  justify-content: center;
  align-items: center;
`,me=c.div`
  position: absolute;
  width: 100%;
  bottom: 0;
  background-color: #fff;
  padding: 10px 10px;
  border-radius: 5px 5px 0 0;
  box-shadow:
    rgba(0, 0, 0, 0.16) 0px 10px 36px 0px,
    rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;
  transition: height 0.3s ease-in-out;
  button {
    padding: 5px 40px;
    font-size: 14px;
    background-color: #6f4cdb;
    color: #fff;
    border-radius: 5px;
  }
`,I=c.div`
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  margin: 0 30px;
  p {
    font-size: 14px;
  }
  div {
    font-size: 12px;
    color: #888888;
  }
  span {
  }
`;function ve(){var S,M;const v=q(),C=T(H),P=Q(),{time:x,count:R}=P.state||{},[a,N]=i.useState({}),[y,m]=i.useState(!1),[$,z]=i.useState([]),[d,h]=i.useState([]),[u,g]=i.useState([]),[he,k]=V(G),[l,D]=i.useState({}),A=T(J),{id:f}=W(),_=async()=>{try{const t=await b.get(`/api/restaurant?restaurantId=${f}`),n=t.data.resultData;N(t.data.resultData),z(n.menuCateList),console.log(t.data.resultData)}catch(t){console.log(t),j.fire({title:"주문에 실패했습니다",text:"다시 한번 시도해주세요",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(n=>{n.isConfirmed})}},E=async()=>{var s,r;const t=ae();console.log(l),console.log(new Date);try{if(C){const o=await b.post("/api/reservation",l,{headers:{Authorization:t}});console.log(o.data.resultData),k(o.data.resultData),B((s=o.data)==null?void 0:s.resultData),j.fire({title:`${x}에 예약이 완료 되었습니다.`,icon:"success",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(p=>{p.isConfirmed&&v("/user")})}else{const o=await b.post("/api/order/with-detail",l,{headers:{Authorization:t}});console.log(o.data.resultData),k(o.data.resultData),B((r=o.data)==null?void 0:r.resultData);const p=x?`${x}에 예약이 완료 되었습니다.`:"예약이 완료 되었습니다.";j.fire({title:p,icon:"success",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(U=>{U.isConfirmed&&v("/user")})}}catch(o){console.log(o),j.fire({title:"예약에 실패했습니다",text:"진행 중인 주문을 확인해 주세요",icon:"error",confirmButtonText:"확인",showConfirmButton:!0,allowOutsideClick:!1}).then(p=>{p.isConfirmed})}},O=()=>{switch(a.categoryId){case 1:return"한식";case 2:return"중식";case 3:return"일식";default:return"기타"}},K=t=>{const n=[...d];n[t].menuCount+=1,h(n);const s=[...u];s[t].menuCount+=1,g(s)},Y=t=>{const n=[...d];n[t].menuCount>1&&(n[t].menuCount-=1,h(n));const s=[...u];s[t].menuCount>1&&(s[t].menuCount-=1,g(s))};return i.useEffect(()=>{_(),console.log(f)},[]),i.useEffect(()=>{const t=window.sessionStorage.getItem("userId");if(C){const s=`${X(new Date).format("YYYY-MM-DD")}T${x}`;D({userId:parseInt(t),restaurantId:parseInt(f),reservationTime:s,reservationPeopleCount:R,userPhone:A.phone.replace(/-/g,""),menuList:[...u]})}else D({userId:t,restaurantId:parseInt(f),orderDetails:[...u]})},[u]),i.useEffect(()=>{console.log("포스트 데이터",l)},[l]),e.jsxs("div",{style:{height:"100vh"},children:[e.jsx("img",{src:`${F}/pic/restaurant/${a.restaurantId}/${(S=a.restaurantPics)==null?void 0:S.filePath}`,alt:"가게 이미지",style:{width:"100%",height:260,position:"relative"}}),e.jsx(ue,{children:e.jsx(Z,{style:{width:"100%",height:"100%"},onClick:()=>v(-1)})}),e.jsxs(le,{children:[e.jsxs("div",{children:[(M=a==null?void 0:a.restaurantAddress)==null?void 0:M.match(/^(?:대구광역시|대구)\s*(.+)/)[1]," ",e.jsx("span",{children:"I"})," ",O()]}),e.jsx("h1",{children:a.restaurantName}),e.jsx("div",{dangerouslySetInnerHTML:{__html:ee.sanitize(String(a.restaurantDescription))}}),e.jsxs("h2",{children:[e.jsx(te,{}),a.restaurantAddress]}),e.jsxs("h2",{style:{marginTop:10},children:[e.jsx(ie,{}),"매장 연락처 : ",a.restaurantNumber]})]}),e.jsx(w,{}),e.jsxs(pe,{children:[e.jsx("h1",{children:"메뉴 선택"}),$.map((t,n)=>e.jsx("div",{children:t.menuList.map(s=>e.jsxs("div",{children:[e.jsxs(xe,{children:[e.jsx("img",{src:`${F}/pic/menu/${s.menuId}/${s==null?void 0:s.menuPic}`,alt:"메뉴 이미지"}),e.jsxs("div",{onClick:()=>console.log(),children:[e.jsx("div",{children:s.menuName}),e.jsxs("span",{children:[s.price.toLocaleString("ko-KR"),"원"]})]}),e.jsx("div",{style:{position:"absolute",right:20,cursor:"pointer"},children:d.some(r=>r.menuId===s.menuId)?e.jsx(se,{onClick:()=>{h(r=>r.filter(o=>o.menuId!==s.menuId)),g(r=>r.filter(o=>o.menuId!==s.menuId)),m(!0)}}):e.jsx(ne,{onClick:()=>{h(r=>[...r,{menuId:s.menuId,menuName:s.menuName,price:s.price,menuCount:1}]),g(r=>[...r,{menuId:s.menuId,menuCount:1}]),m(!0)}})})]}),e.jsx(w,{style:{height:1,backgroundColor:"#eee",marginBottom:10}})]},s.menuId))},n))]}),e.jsxs(me,{children:[e.jsx(L,{children:y?e.jsx(oe,{style:{color:"#6F6F6F",cursor:"pointer"},onClick:()=>m(!1)}):e.jsx(re,{style:{color:"#6F6F6F",cursor:"pointer"},onClick:()=>m(!0)})}),y&&e.jsxs("div",{children:[d.map((t,n)=>e.jsxs("div",{children:[e.jsxs(I,{children:[e.jsx("p",{children:t.menuName}),e.jsx("div",{children:t.price.toLocaleString()})]}),e.jsxs(I,{style:{margin:"5px 30px"},children:[e.jsxs("div",{style:{display:"flex",alignItems:"center",gap:15},children:[e.jsx("p",{onClick:()=>Y(n),children:e.jsx(ce,{className:"cursor-pointer"})}),e.jsx("p",{children:t.menuCount}),e.jsx("p",{onClick:()=>K(n),children:e.jsx(de,{className:"cursor-pointer"})})]}),e.jsxs("div",{children:[(t.menuCount*t.price).toLocaleString("ko-KR"),"원"]})]})]},n)),e.jsx(w,{style:{height:1,backgroundColor:"#eee",margin:"10px 0"}}),e.jsxs(I,{style:{marginBottom:10},children:[e.jsxs("p",{children:["총 수량 ",d.reduce((t,n)=>t+n.menuCount,0),"개"]}),e.jsxs("p",{children:["총"," ",d.reduce((t,n)=>t+n.menuCount*n.price,0).toLocaleString("ko-KR"),"원"]})]}),e.jsx(L,{children:e.jsx("button",{onClick:()=>E(),children:C?"예약하기":"결제하기"})})]})]})]})}export{ve as default};
