var fe=Object.defineProperty;var ge=(o,e,t)=>e in o?fe(o,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):o[e]=t;var G=(o,e,t)=>ge(o,typeof e!="symbol"?e+"":e,t);import{c as i,aE as T,j as s,aF as ne,R as ke,aG as me,u as xe,au as be,V as ve,an as ye,J as we,K as te,P as Ce,q as Q,a1 as K}from"./index-QD4sUlUr.js";import{F as Le}from"./index-2Luirxk7.js";/* empty css                      */const J=typeof window<"u"&&typeof document<"u"?i.useLayoutEffect:i.useEffect,b=(o,e,t)=>{J(()=>{if(!o||!t)return;const r=function(){for(var a=arguments.length,x=new Array(a),d=0;d<a;d++)x[d]=arguments[d];return x===void 0?t(o):t(o,...x)};return kakao.maps.event.addListener(o,e,r),()=>{kakao.maps.event.removeListener(o,e,r)}},[o,e,t])},re="__react-kakao-maps-sdk__";let V=function(o){return o[o.INITIALIZED=0]="INITIALIZED",o[o.LOADING=1]="LOADING",o[o.SUCCESS=2]="SUCCESS",o[o.FAILURE=3]="FAILURE",o}({});const Ee=`${re}_Loader`,k=class k{constructor(e){G(this,"callbacks",[]);G(this,"done",!1);G(this,"loading",!1);G(this,"errors",[]);let{appkey:t,id:r=Ee,libraries:a=[],nonce:x,retries:d=3,url:w="//dapi.kakao.com/v2/maps/sdk.js"}=e;if(this.id=r,this.appkey=t,this.libraries=a,this.nonce=x,this.retries=d,this.url=w,k.instance&&!k.equalOptions(this.options,k.instance.options)&&!k.equalOptions(this.options,k.instance.options))switch(k.instance.status){case V.FAILURE:throw new Error(`Loader must not be called again with different options. 
${JSON.stringify(this.options,null,2)}
!==
${JSON.stringify(k.instance.options,null,2)}`);default:k.instance.reset(),k.instance=this;break}return k.instance||(k.instance=this),k.instance}get options(){return{appkey:this.appkey,id:this.id,libraries:this.libraries,nonce:this.nonce,retries:this.retries,url:this.url}}static addLoadEventLisnter(e){return window.kakao&&window.kakao.maps&&window.kakao.maps.load(e),k.loadEventCallback.add(e),e}static removeLoadEventLisnter(e){return k.loadEventCallback.delete(e)}load(){return new Promise((e,t)=>{this.loadCallback(r=>{r?t(r):e(window.kakao)})})}get status(){return this.onEvent?V.FAILURE:this.done?V.SUCCESS:this.loading?V.LOADING:V.INITIALIZED}get failed(){return this.done&&!this.loading&&this.errors.length>=this.retries+1}loadCallback(e){this.callbacks.push(e),this.execute()}resetIfRetryingFailed(){this.failed&&this.reset()}reset(){this.deleteScript(),this.done=!0,this.loading=!1,this.errors=[],this.onEvent=void 0}execute(){if(this.resetIfRetryingFailed(),this.done)this.callback();else{if(window.kakao&&window.kakao.maps){console.warn("Kakao Maps이 이미 외부 요소에 의해 로딩되어 있습니다.설정한 옵션과 일치 하지 않을 수 있으며, 이에 따른 예상치 동작이 발생할 수 있습니다."),window.kakao.maps.load(this.callback);return}this.loading||(this.loading=!0,this.setScript())}}setScript(){document.getElementById(this.id)&&this.callback();const e=this.createUrl(),t=document.createElement("script");t.id=this.id,t.type="text/javascript",t.src=e,t.onerror=this.loadErrorCallback.bind(this),t.onload=this.callback.bind(this),t.defer=!0,t.async=!0,this.nonce&&(t.nonce=this.nonce),document.head.appendChild(t)}loadErrorCallback(e){if(this.errors.push(e),this.errors.length<=this.retries){const t=this.errors.length*2**this.errors.length;console.log(`Failed to load Kakao Maps script, retrying in ${t} ms.`),setTimeout(()=>{this.deleteScript(),this.setScript()},t)}else this.done=!0,this.loading=!1,this.onEvent=this.errors[this.errors.length-1],this.callbacks.forEach(t=>{t(this.onEvent)}),this.callbacks=[],k.loadEventCallback.forEach(t=>{t(this.onEvent)})}createUrl(){let e=this.url;return e+=`?appkey=${this.appkey}`,this.libraries.length&&(e+=`&libraries=${this.libraries.join(",")}`),e+="&autoload=false",e}deleteScript(){const e=document.getElementById(this.id);e&&e.remove()}callback(){kakao.maps.load(()=>{k.instance.done=!0,k.instance.loading=!1,k.instance.callbacks.forEach(e=>{e(k.instance.onEvent)}),k.instance.callbacks=[],k.loadEventCallback.forEach(e=>{e(k.instance.onEvent)})})}static equalOptions(e,t){if(e.appkey!==t.appkey||e.id!==t.id||e.libraries.length!==t.libraries.length)return!1;for(let r=0;r<e.libraries.length;++r)if(e.libraries[r]!==t.libraries[r])return!1;return!(e.nonce!==t.nonce||e.retries!==t.retries||e.url!==t.url)}};G(k,"loadEventCallback",new Set);let X=k;const m=function(o,e){for(var t=arguments.length,r=new Array(t>2?t-2:0),a=2;a<t;a++)r[a-2]=arguments[a];J(()=>{!o||r.every(x=>typeof x>"u")||o[e].call(o,...r)},[o,e,...r])},oe=T.createContext(void 0),Ie=T.forwardRef(function(e,t){let{id:r,as:a,children:x,center:d,isPanto:w=!1,padding:L=32,disableDoubleClick:h,disableDoubleClickZoom:f,draggable:v,zoomable:I,keyboardShortcuts:l,level:M,maxLevel:j,minLevel:D,mapTypeId:S,projectionId:E,scrollwheel:z,tileAnimation:O,onBoundsChanged:$,onCenterChanged:P,onClick:p,onDoubleClick:F,onDrag:U,onDragEnd:H,onDragStart:B,onIdle:Z,onMaptypeidChanged:q,onMouseMove:n,onRightClick:c,onTileLoaded:g,onZoomChanged:y,onZoomStart:C,onCreate:N,...de}=e;const ce=a||"div",[Y,ue]=i.useState(!1),[u,pe]=i.useState(),ee=i.useRef(null);return J(()=>{const A=X.addLoadEventLisnter(R=>ue(!R));return()=>{X.removeLoadEventLisnter(A)}},[]),J(()=>{if(!Y)return;const A=ee.current;if(!A)return;const R="lat"in d?new kakao.maps.LatLng(d.lat,d.lng):new kakao.maps.Coords(d.x,d.y),he=new kakao.maps.Map(A,{center:R,disableDoubleClick:h,disableDoubleClickZoom:f,draggable:v,keyboardShortcuts:l,level:M,mapTypeId:typeof S=="string"?kakao.maps.MapTypeId[S]:S,projectionId:E,scrollwheel:z,tileAnimation:O});return pe(he),()=>{A.innerHTML=""}},[Y,h,f,O]),i.useImperativeHandle(t,()=>u,[u]),J(()=>{!u||!N||N(u)},[u,N]),J(()=>{if(!u)return;let A=u.getCenter();A instanceof kakao.maps.Coords&&(A=A.toLatLng());const R="lat"in d?new kakao.maps.LatLng(d.lat,d.lng):new kakao.maps.Coords(d.x,d.y);R instanceof kakao.maps.LatLng&&R.equals(A)||R instanceof kakao.maps.Coords&&R.toLatLng().equals(A)||(w?u.panTo(R,L):u.setCenter(R))},[u,d.lat,d.lng,d.x,d.y]),m(u,"setDraggable",v),m(u,"setZoomable",I),m(u,"setKeyboardShortcuts",l),m(u,"setLevel",M),m(u,"setMapTypeId",Y?typeof S=="string"?kakao.maps.MapTypeId[S]:S:void 0),m(u,"setProjectionId",E),m(u,"setMinLevel",j),m(u,"setMaxLevel",D),b(u,"bounds_changed",$),b(u,"center_changed",P),b(u,"click",p),b(u,"dblclick",F),b(u,"drag",U),b(u,"dragstart",B),b(u,"dragend",H),b(u,"idle",Z),b(u,"maptypeid_changed",q),b(u,"mousemove",n),b(u,"rightclick",c),b(u,"tilesloaded",g),b(u,"zoom_changed",y),b(u,"zoom_start",C),s.jsxs(s.Fragment,{children:[s.jsx(ce,{id:r||`${re}_Map`,...de,ref:ee}),u&&s.jsx(oe.Provider,{value:u,children:x})]})}),ie=o=>{const e=i.useContext(oe);if(!e)throw new Error(`${o?o+" Component":"useMap"} must exist inside Map Component!`);return e},Me=T.forwardRef(function(e,t){let{map:r,position:a,marker:x,children:d,altitude:w,disableAutoPan:L,range:h,removable:f,zIndex:v,onCreate:I}=e;const l=i.useMemo(()=>{const j=document.createElement("div");return j.style.display="none",new kakao.maps.InfoWindow({altitude:w,disableAutoPan:L,range:h,removable:f,zIndex:v,content:j,position:a})},[L,f]),M=i.useMemo(()=>l.getContent(),[l]);return i.useImperativeHandle(t,()=>l,[l]),i.useLayoutEffect(()=>(l.open(r,x),()=>{l.close()}),[r,x]),i.useLayoutEffect(()=>{I&&I(l)},[l,I]),m(l,"setPosition",a),m(l,"setAltitude",w),m(l,"setRange",h),m(l,"setZIndex",v),ne.createPortal(d,M.parentElement??M)}),le=T.createContext(void 0),je=T.forwardRef(function(e,t){let{map:r,position:a,children:x,altitude:d,clickable:w,draggable:L,image:h,infoWindowOptions:f,onCreate:v,onClick:I,onDragEnd:l,onDragStart:M,onMouseOut:j,onMouseOver:D,opacity:S,range:E,title:z,zIndex:O}=e;const $=i.useContext(le),P=i.useMemo(()=>{var F,U,H,B,Z,q,n,c,g,y,C,N;return h&&new kakao.maps.MarkerImage(h.src,new kakao.maps.Size(h.size.width,h.size.height),{alt:(F=h.options)==null?void 0:F.alt,coords:(U=h.options)==null?void 0:U.coords,offset:((H=h.options)==null?void 0:H.offset)&&new kakao.maps.Point((B=h.options)==null?void 0:B.offset.x,(Z=h.options)==null?void 0:Z.offset.y),shape:(q=h.options)==null?void 0:q.shape,spriteOrigin:((n=h.options)==null?void 0:n.spriteOrigin)&&new kakao.maps.Point((c=h.options)==null?void 0:c.spriteOrigin.x,(g=h.options)==null?void 0:g.spriteOrigin.y),spriteSize:((y=h.options)==null?void 0:y.spriteSize)&&new kakao.maps.Size((C=h.options)==null?void 0:C.spriteSize.width,(N=h.options)==null?void 0:N.spriteSize.height)})},[JSON.stringify(h)]),p=i.useMemo(()=>new kakao.maps.Marker({altitude:d,clickable:w,draggable:L,image:P,opacity:S,range:E,title:z,zIndex:O,position:a}),[]);return i.useImperativeHandle(t,()=>p,[p]),i.useLayoutEffect(()=>$?($.addMarker(p,!0),()=>$.removeMarker(p,!0)):(p.setMap(r),()=>p.setMap(null)),[r,$,p]),i.useLayoutEffect(()=>{v&&v(p)},[p,v]),m(p,"setPosition",a),m(p,"setImage",P),m(p,"setAltitude",d),m(p,"setClickable",w),m(p,"setDraggable",L),m(p,"setOpacity",S),m(p,"setRange",E),m(p,"setRange",E),m(p,"setTitle",z),m(p,"setTitle",z),m(p,"setZIndex",O),b(p,"click",I),b(p,"dragstart",M),b(p,"dragend",l),b(p,"mouseout",j),b(p,"mouseover",D),x?s.jsx(Me,{position:a,map:r,marker:p,altitude:f==null?void 0:f.altitude,disableAutoPan:f==null?void 0:f.disableAutoPan,range:f==null?void 0:f.range,removable:f==null?void 0:f.removable,zIndex:f==null?void 0:f.zIndex,children:x}):null}),ae=T.forwardRef(function(e,t){let{position:r,...a}=e;const x=ie("MapMarker"),d=i.useMemo(()=>"lat"in r?new kakao.maps.LatLng(r.lat,r.lng):new kakao.maps.Coords(r.x,r.y).toLatLng(),[r.lat,r.lng,r.x,r.y]);return s.jsx(je,{map:x,position:d,...a,ref:t})}),se=T.forwardRef(function(e,t){let{position:r,children:a,clickable:x,xAnchor:d,yAnchor:w,zIndex:L,onCreate:h}=e;const f=i.useContext(le),v=ie("CustomOverlayMap"),I=i.useMemo(()=>new kakao.maps.LatLng(r.lat,r.lng),[r.lat,r.lng]),l=i.useMemo(()=>{const j=document.createElement("div");return j.style.display="none",new kakao.maps.CustomOverlay({clickable:x,xAnchor:d,yAnchor:w,zIndex:L,position:I,content:j})},[x,d,w]),M=i.useMemo(()=>l.getContent(),[l]);return i.useImperativeHandle(t,()=>l,[l]),i.useLayoutEffect(()=>{if(v)return f?f.addMarker(l,!0):l.setMap(v),()=>{f?f.removeMarker(l,!0):l.setMap(null)}},[v,f,l]),i.useLayoutEffect(()=>{h&&h(l)},[l,h]),m(l,"setPosition",I),m(l,"setZIndex",L),M.parentElement&&ne.createPortal(a,M.parentElement)}),Se=o=>{const[e,t]=i.useState([!0,void 0]);return i.useEffect(()=>{new X({...o}).load().then(()=>t([!1,void 0])).catch(r=>{t([!1,r])})},[JSON.stringify(o)]),e},Ae=K.div`
  position: absolute;
  width: 100%;
  height: ${o=>`${o.height}px`};
  bottom: 0px;
  z-index: 1;
  background-color: #fff;
  border-radius: 5px 5px 0 0;
  padding-left: 20px;
  overflow-y: auto;
  transition: height 0.3s ease-in-out;
  z-index: 10;
`,Re=K.div`
  width: 100px;
  height: 5px;
  margin-top: 10px;
  background-color: #eee;
  border-radius: 2px;
  margin-bottom: 20px;
  cursor: pointer;
`,W=K.div`
  padding: 3px 10px;
  background-color: #ddd;
  color: #fff;
  display: inline-block;
  font-size: 10px;
  border-radius: 10px;
  cursor: pointer;
`,_=K.div`
  display: flex;
  align-items: center;
  gap: 5px;
  width: 100%;
  margin-bottom: 5px;
`,ze=K.div`
  position: absolute;
  display: flex;
  justify-content: center;
  top: 2%;
  width: 100%;
  z-index: 10;
  color: #333;
  div {
    display: flex;
    gap: 10px;
    background-color: #fff;
    width: calc(100% - 40px);
    padding: 5px 10px;
    border-radius: 2px;
    box-shadow:
      0px 20px 25px -5px rgba(0, 0, 0, 0.1),
      0px 10px 10px -5px rgba(0, 0, 0, 0.04);
  }

  input {
    width: 100%;
  }
`,De=K.div`
  position: relative;
  background-color: #fff;
  padding: 10px;
  color: #333;
  font-size: 12px;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  text-align: center;
  button {
    position: absolute;
    top: 5px;
    right: 5px;
    background: none;
    border: none;
    cursor: pointer;
  }
  h3 {
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 5px;
  }
  p {
    font-size: 12px;
    color: #999;
  }
`,$e=K.div`
  background-color: #6f4cdb;
  color: #fff;
  border-radius: 5px;
  padding: 5px;
  font-size: 10px;
  position: absolute;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  left: 10px;
  bottom: -10px;
`;function Te(){Se({appkey:"223f69f1eff1187f853d5ddbe870fde4",libraries:["clusterer","drawing","services"]});const[o,e]=i.useState(!1),[t,r]=i.useState(!1),[a,x]=ke(me),[d,w]=i.useState(null),[L,h]=i.useState(250),[f,v]=i.useState([]),[I,l]=i.useState([]),[M,j]=i.useState({}),[D,S]=i.useState(),[E,z]=i.useState(1),[O,$]=i.useState(!0),P=xe(),p=async()=>{l([]);try{const n=await Q.get(`/api/restaurant/around?orderFilter=${E}&userLat=${a==null?void 0:a.latitude}&userLng=${a==null?void 0:a.longitude}`);console.log(n);const c=n.data.resultData;v([...c]),c.map(g=>{l(y=>[...y,{title:g.restaurantName,address:g.restaurantAddress,position:{lat:g.lat,lng:g.lng}}])})}catch(n){console.log(n)}finally{setTimeout(()=>$(!1),1e3)}},F=async(n,c)=>{l([]);try{const g=await Q.get(`/api/restaurant/around?orderFilter=${E}&userLat=${n}&userLng=${c}`);console.log(g);const y=g.data.resultData;v([...y]),y.map(C=>{l(N=>[...N,{title:C.restaurantName,address:C.restaurantAddress,position:{lat:C.lat,lng:C.lng}}])})}catch(g){console.log(g)}},U=async()=>{l([]);try{const c=(await Q.get(`/api/restaurant/around?searchFilter=${D}&userLat=${a.latitude}&userLng=${a.longitude}`)).data.resultData;v(c),c.map(g=>{l(y=>[...y,{title:g.restaurantName,address:g.restaurantAddress,position:{lat:g.lat,lng:g.lng}}])}),console.log(c)}catch(n){console.log(n)}},H=n=>{const{latitude:c,longitude:g}=n.coords;x({latitude:c,longitude:g})},B=n=>{console.log(n)};i.useEffect(()=>{l([]),p()},[E,a]),i.useEffect(()=>{navigator.geolocation.getCurrentPosition(H,B)},[]),i.useEffect(()=>{(()=>{var g;if((g=window.kakao)!=null&&g.maps){e(!0);return}const c=document.createElement("script");return c.async=!0,c.src="//dapi.kakao.com/v2/maps/sdk.js?appkey=223f69f1eff1187f853d5ddbe870fde4&autoload=false&libraries=services",c.addEventListener("load",()=>{window.kakao.maps.load(()=>e(!0))}),document.head.appendChild(c),()=>document.head.removeChild(c)})()},[]);const Z=()=>{r(n=>!n),h(n=>{if(n!==250){const c=document.querySelector(".scrollable-content");c&&(c.scrollTop=0)}return n===250?window.innerHeight:250})};if(!o)return s.jsx("div",{children:"지도를 불러오는 중입니다..."});const q=n=>{if(typeof n!="number"){P("/notfound");return}P(`/user/restaurant/detail/${n}`,{state:{from:"/user/restaurant"}})};return s.jsx(s.Fragment,{children:s.jsxs("div",{className:"w-full h-dvh overflow-hidden overflow-y-scroll scrollbar-hide relative",children:[O&&s.jsx("div",{className:"absolute inset-0 z-50",children:s.jsx(be,{message:"주변 식당 찾는중..."})}),s.jsxs(Ie,{center:{lat:a==null?void 0:a.latitude,lng:a==null?void 0:a.longitude},style:{width:"100%",height:"100%"},level:3,onCenterChanged:n=>{const c=n.getCenter();w({latitude:c.getLat(),longitude:c.getLng()})},onTouchEnd:()=>{d&&x(d),F(a.latitude,a.longitude)},onMouseUp:()=>{d&&x(d),F(a.latitude,a.longitude)},children:[s.jsxs("div",{style:{position:"relative"},children:[s.jsx(ae,{position:{lat:a==null?void 0:a.latitude,lng:a==null?void 0:a.longitude}}),s.jsx(se,{position:{lat:a==null?void 0:a.latitude,lng:a==null?void 0:a.longitude},children:s.jsx($e,{children:"내 위치"})})]}),(I??[]).map((n,c)=>s.jsxs("div",{children:[s.jsx(ae,{position:n.position,image:{src:"https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",size:{width:24,height:35}},clickable:!0,onClick:()=>j(c)}),M===c&&s.jsx(se,{position:n.position,children:s.jsxs(De,{children:[s.jsx("button",{onClick:()=>j(null),children:"❌"}),s.jsx("h3",{children:n.title}),s.jsx("p",{children:n.address})]})})]},c))]}),s.jsx(ze,{children:s.jsxs("div",{children:[s.jsx(ve,{style:{width:24,height:24,cursor:"pointer"},onClick:()=>P("/user")}),s.jsx("input",{type:"text",placeholder:"검색어를 입력해 주세요",value:D,onChange:n=>S(n.target.value)}),s.jsx(ye,{style:{width:24,height:24,cursor:"pointer"},onClick:()=>{U(),S("")}})]})}),s.jsxs(Ae,{className:"scrollbar-hide",isOpen:t,height:L,children:[s.jsx("div",{style:{display:"flex",justifyContent:"center",paddingRight:20},onClick:Z,children:s.jsx(Re,{})}),s.jsxs(_,{style:{gap:10,marginBottom:10},children:[s.jsx(W,{style:{backgroundColor:E===1&&"#6f4cdb"},onClick:()=>z(1),children:"거리순"}),s.jsx(W,{style:{backgroundColor:E===2&&"#6f4cdb"},onClick:()=>z(2),children:"별점순"}),s.jsx(W,{style:{backgroundColor:E===3&&"#6f4cdb"},onClick:()=>z(3),children:"빠른식사순"})]}),s.jsx("div",{className:"scrollable-content scrollbar-hide mt-3",style:{maxHeight:L===250?180:840,overflowY:"scroll",overflowX:"hidden"},children:f.map(n=>{var c,g;return s.jsxs("div",{onClick:()=>q(n.restaurantId),className:"mb-5 cursor-pointer",children:[s.jsxs(_,{children:[s.jsx("span",{children:n.restaurantName}),s.jsxs("span",{style:{fontSize:12},onClick:()=>console.log(n.restaurantAddress),children:["식사시간 : 약 ",(c=n.avgRestaurant)==null?void 0:c.toFixed(0),"분"]})]}),s.jsxs(_,{children:[s.jsx(Le,{style:{width:10,height:10,color:"E1FF00"}}),s.jsx("span",{style:{fontWeight:700,fontSize:8},children:n.avgRating.toFixed(1)}),s.jsxs("span",{style:{fontSize:8,color:"#BABABA"},children:[n==null?void 0:n.restaurantAddress.match(/^(?:대구광역시|대구)\s*(.+)/)[1]," ","· 한식"]})]}),s.jsx(we,{slidesPerView:3,spaceBetween:30,children:(g=n.restaurantArroundPicList)==null?void 0:g.map((y,C)=>y?s.jsx(te,{children:s.jsx("img",{src:`${Ce}/pic/restaurant/${n.restaurantId}/${y==null?void 0:y.filePath}`,style:{minWidth:140,width:140,height:140,objectFit:"cover"}},C)},C):s.jsx(te,{children:s.jsx("img",{src:"/restaurant_default.png",style:{minWidth:140,width:140,height:140,objectFit:"cover"}},C)},C))})]},n.restaurantId)})})]})]})})}export{Te as default};
