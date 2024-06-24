(function(){"use strict";var a={499:function(a,e,o){var t=o(130),r=o(768),l=o(232),n=o(144);const s=a=>((0,r.Qi)("data-v-07a9d10e"),a=a(),(0,r.jt)(),a),i={key:0,class:"dialog-overlay"},u={class:"dialog"},d=s((()=>(0,r.Lk)("h3",null,"Log in",-1))),c={style:{position:"relative",left:"0%"}};var m={__name:"LoginDialog",props:{visible:{type:Boolean,default:!1}},emits:["login","close","register"],setup(a,{emit:e}){const o=e,l=(0,n.KR)(null),s=(0,n.KR)({id:"",fakename:"",password:""}),m={id:[{required:!0,message:"请输入ID",trigger:"blur"}],fakename:[{required:!0,message:"请输入假名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}]},v=(0,r.WQ)("login-info"),A=async a=>{a&&await a.validate((a=>{a?(console.log("id: ",s.value.id," submitted successfully!"),v.value.userId=s.value.id,v.value.fakeName=s.value.fakename,v.value.userPasswd=s.value.password,o("login"),h()):alert("login failed!")}))},g=async a=>{a&&await a.validate((a=>{a?(console.log("id: ",s.value.id," submitted successfully!"),v.value.userId=s.value.id,v.value.fakeName=s.value.fakename,v.value.userPasswd=s.value.password,o("register"),h()):alert("Register failed!")}))},h=()=>{o("close")};return(e,o)=>{const n=(0,r.g2)("el-input"),v=(0,r.g2)("el-form-item"),p=(0,r.g2)("el-button"),f=(0,r.g2)("el-form");return a.visible?((0,r.uX)(),(0,r.CE)("div",i,[(0,r.Lk)("div",u,[d,(0,r.bF)(f,{ref_key:"ruleFormRef",ref:l,rules:m,model:s.value,"label-width":"auto",style:{"max-width":"600px",opacity:"1"}},{default:(0,r.k6)((()=>[(0,r.bF)(v,{label:"ID",prop:"id"},{default:(0,r.k6)((()=>[(0,r.bF)(n,{modelValue:s.value.id,"onUpdate:modelValue":o[0]||(o[0]=a=>s.value.id=a)},null,8,["modelValue"])])),_:1}),(0,r.bF)(v,{label:"假名",prop:"fakename"},{default:(0,r.k6)((()=>[(0,r.bF)(n,{modelValue:s.value.fakename,"onUpdate:modelValue":o[1]||(o[1]=a=>s.value.fakename=a)},null,8,["modelValue"])])),_:1}),(0,r.bF)(v,{label:"密码",prop:"password"},{default:(0,r.k6)((()=>[(0,r.bF)(n,{modelValue:s.value.password,"onUpdate:modelValue":o[2]||(o[2]=a=>s.value.password=a)},null,8,["modelValue"])])),_:1}),(0,r.Lk)("div",c,[(0,r.bF)(p,{type:"warning",onClick:o[3]||(o[3]=(0,t.D$)((a=>g(l.value)),["prevent"]))},{default:(0,r.k6)((()=>[(0,r.eW)("注册")])),_:1}),(0,r.bF)(p,{type:"primary",onClick:o[4]||(o[4]=(0,t.D$)((a=>A(l.value)),["prevent"]))},{default:(0,r.k6)((()=>[(0,r.eW)("登录")])),_:1}),(0,r.bF)(p,{onClick:h},{default:(0,r.k6)((()=>[(0,r.eW)("取消")])),_:1})])])),_:1},8,["model"])])])):(0,r.Q3)("",!0)}}},v=o(241);const A=(0,v.A)(m,[["__scopeId","data-v-07a9d10e"]]);var g=A;const h=a=>((0,r.Qi)("data-v-83f84024"),a=a(),(0,r.jt)(),a),p={key:0,class:"dialog-overlay"},f={class:"dialog"},C=h((()=>(0,r.Lk)("h3",null,"Create Room",-1))),I={style:{position:"relative",left:"10%"}};var b={__name:"CreateRoomDialog",props:{visible:{type:Boolean,default:!1}},emits:["createroom","close"],setup(a,{emit:e}){const o=e,l=(0,n.KR)(null),s=(0,n.KR)({roomname:""}),i={roomname:[{required:!0,message:"请输入房间名",trigger:"blur"}]},u=a=>{a.preventDefault()},d=async a=>{a&&await a.validate((a=>{a?(console.log("id: ",s.value.roomname," submitted successfully!"),o("createroom",s.value.roomname),c()):alert("login failed!")}))},c=()=>{o("close")};return(e,o)=>{const n=(0,r.g2)("el-input"),m=(0,r.g2)("el-form-item"),v=(0,r.g2)("el-button"),A=(0,r.g2)("el-form");return a.visible?((0,r.uX)(),(0,r.CE)("div",p,[(0,r.Lk)("div",f,[C,(0,r.bF)(A,{ref_key:"ruleFormRef",ref:l,rules:i,model:s.value,"label-width":"auto",style:{"max-width":"600px"}},{default:(0,r.k6)((()=>[(0,r.bF)(m,{label:"房间名",prop:"roomname"},{default:(0,r.k6)((()=>[(0,r.bF)(n,{modelValue:s.value.roomname,"onUpdate:modelValue":o[0]||(o[0]=a=>s.value.roomname=a),onKeyup:(0,t.jR)(u,["enter"])},null,8,["modelValue"])])),_:1}),(0,r.Lk)("div",I,[(0,r.bF)(v,{type:"primary",onClick:o[1]||(o[1]=(0,t.D$)((a=>d(l.value)),["prevent"]))},{default:(0,r.k6)((()=>[(0,r.eW)("创建")])),_:1}),(0,r.bF)(v,{onClick:(0,t.D$)(c,["prevent"])},{default:(0,r.k6)((()=>[(0,r.eW)("取消")])),_:1})])])),_:1},8,["model"])])])):(0,r.Q3)("",!0)}}};const k=(0,v.A)(b,[["__scopeId","data-v-83f84024"]]);var w=k;const y={idd:"top-bar"},E={id:"user-bar"},B={id:"room-name-bar"},Q={id:"title"};var F={__name:"TopBar",emits:["log-in","log-out","createroom","random-change-user-avatar","register"],setup(a,{emit:e}){const o=e,s=(0,r.EW)((()=>i.value.headPhoto?i.value.headPhoto:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png")),i=(0,r.WQ)("login-info"),u=(0,r.WQ)("room-info"),d=(0,r.EW)((()=>u.value.currentRoomName?u.value.currentRoomName:"")),c=(0,r.EW)((()=>i.value.isLogIn?"logout":"login")),m=(0,n.KR)(!1),v=(0,n.KR)(!1),A=()=>{i.value.isLogIn?o("log-out"):m.value=!0},h=()=>{v.value=!0},p=()=>{o("log-in"),m.value=!1},f=()=>{o("register"),m.value=!1},C=a=>{o("createroom",a),v.value=!1},I=()=>{m.value=!1},b=()=>{v.value=!1},k=()=>{o("random-change-user-avatar")};return(a,e)=>{const o=(0,r.g2)("el-avatar"),n=(0,r.g2)("el-button");return(0,r.uX)(),(0,r.CE)("div",y,[(0,r.Lk)("div",E,[(0,r.bF)(o,{src:s.value,alt:"Avatar",id:"avatar",shape:"circle",fit:"cover",onDblclick:k},null,8,["src"]),(0,r.Lk)("div",null,[(0,r.bF)(n,{type:"primary",round:"",onClick:(0,t.D$)(A,["prevent"]),id:"log-button"},{default:(0,r.k6)((()=>[(0,r.eW)((0,l.v_)(c.value),1)])),_:1}),(0,r.bF)(n,{type:"primary",circle:"",id:"create-room-button",onClick:(0,t.D$)(h,["prevent"])},{default:(0,r.k6)((()=>[(0,r.eW)("+")])),_:1})])]),(0,r.Lk)("div",B,[(0,r.Lk)("p",Q,"🏡"+(0,l.v_)(d.value),1)]),(0,r.bF)(g,{visible:m.value,onLogin:p,onClose:I,onRegister:f},null,8,["visible"]),(0,r.bF)(w,{visible:v.value,onClose:b,onCreateroom:C},null,8,["visible"])])}}};const x=(0,v.A)(F,[["__scopeId","data-v-cb1ba222"]]);var L=x;const W={class:"info"},R={class:"room-name"},K={class:"newest-message"};var T={__name:"RoomWindow",props:{roomname:{type:String,required:!0},newestMessage:{type:String,required:!0},roomAvatar:{type:String,required:!0}},emits:["random-update-room-avatar"],setup(a,{emit:e}){const o=e,t=a,n=(a,e)=>a.length>e?a.slice(0,e)+"...":a,s=(0,r.WQ)("room-info"),i=()=>{s.value.currentRoomName=t.roomname},u=()=>{o("random-update-room-avatar",t.roomname)},d=(0,r.EW)((()=>({display:"flex",alignItems:"fex-start",left:"inherit",right:"inherit",height:"15%",borderBottom:"0.5px solid rgb(83, 76, 76)",backgroundColor:s.value.currentRoomName===t.roomname?"rgb(187, 186, 186)":"#f5f5f5"})));return(e,o)=>{const t=(0,r.g2)("el-avatar");return(0,r.uX)(),(0,r.CE)("div",{style:(0,l.Tr)(d.value),onClick:i,class:"room-window"},[(0,r.bF)(t,{src:a.roomAvatar,alt:"Avatar",class:"avatar",shape:"square",fit:"fill",size:52,onDblclick:u},null,8,["src"]),(0,r.Lk)("div",W,[(0,r.Lk)("p",R,(0,l.v_)(n(a.roomname,11)),1),(0,r.Lk)("div",K,(0,l.v_)(n(a.newestMessage,15)),1)])],4)}}};const S=(0,v.A)(T,[["__scopeId","data-v-9be8d75c"]]);var U=S;const G={class:"side-bar"};var _={__name:"SideBar",emits:["random-update-room-avatar"],setup(a,{emit:e}){const o=e,t=(0,r.WQ)("room-info"),l=(0,r.EW)((()=>t.value.roomList)),n=a=>a.length>0?a[a.length-1].content:"",s=a=>{o("random-update-room-avatar",a)};return(a,e)=>((0,r.uX)(),(0,r.CE)("div",G,[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(l.value,((a,e)=>((0,r.uX)(),(0,r.Wv)(U,{key:e,roomname:a.roomName,newestMessage:n(a.messages),roomAvatar:a.roomAvatar,onRandomUpdateRoomAvatar:s},null,8,["roomname","newestMessage","roomAvatar"])))),128))]))}};const j=(0,v.A)(_,[["__scopeId","data-v-1f3b26bf"]]);var D=j;const X={class:"chat-window"};function J(a,e,o,t,l,n){const s=(0,r.g2)("ChatBubble");return(0,r.uX)(),(0,r.CE)("div",X,[(0,r.Lk)("div",{ref:"scrollBox",onMousewheel:e[0]||(e[0]=(...a)=>t.handleScroll&&t.handleScroll(...a)),class:"scroll-box"},[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(t.items,((a,e)=>((0,r.uX)(),(0,r.Wv)(s,{key:e,content:a.content,avatar:a.avatar,username:a.senderFakeName,same:t.isUser(a),type:a.type},null,8,["content","avatar","username","same","type"])))),128))],544)])}const N={class:"username"},z={key:0},P={key:1},V={key:2,class:"doc-type-layout"};function M(a,e,o,t,n,s){const i=(0,r.g2)("el-avatar"),u=(0,r.g2)("el-image"),d=(0,r.g2)("downloadlink");return(0,r.uX)(),(0,r.CE)("div",{class:(0,l.C4)({"chat-bubble":!o.same,"chat-bubble-reverse":o.same})},[(0,r.bF)(i,{src:o.avatar,alt:"Avatar",class:"avatar",shape:"circle",fit:"cover",size:40},null,8,["src"]),(0,r.Lk)("div",{class:(0,l.C4)({c1:!o.same,"c1-reverse":o.same})},[(0,r.Lk)("div",N,(0,l.v_)(o.username),1),(0,r.Lk)("div",{class:(0,l.C4)({"chat-right_triangle":o.same,"chat-left_triangle":!o.same})},null,2),(0,r.Lk)("div",{class:(0,l.C4)({"content-container":!o.same,"content-container-reverse":o.same})},[(0,r.Lk)("div",{class:(0,l.C4)({myContent:o.same,content:!o.same})},["text"===o.type?((0,r.uX)(),(0,r.CE)("div",z,(0,l.v_)(o.content),1)):"img"===o.type?((0,r.uX)(),(0,r.CE)("div",P,[(0,r.bF)(u,{class:"limited-size-image",src:o.content},null,8,["src"])])):"doc"===o.type?((0,r.uX)(),(0,r.CE)("div",V,[(0,r.bF)(i,{src:s.fileicon,alt:"Avatar",class:"avatar",shape:"square",fit:"cover",size:"40"},null,8,["src"]),(0,r.bF)(d,{fileUrl:o.content,linkText:""},null,8,["fileUrl"])])):(0,r.Q3)("",!0)],2)],2)],2)],2)}const q=["href","download"];function O(a,e,o,t,n,s){return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("a",{href:o.fileUrl,download:s.fileName,onClick:e[0]||(e[0]=(...a)=>s.downloadFile&&s.downloadFile(...a))},(0,l.v_)(s.fileName),9,q)])}var H={name:"Downloadlink",props:{fileUrl:{type:String,required:!0}},methods:{downloadFile(a){a.target.download&&(a.preventDefault(),this.downloadUsingAnchor())},downloadUsingAnchor(){const a=document.createElement("a");a.href=this.fileUrl,a.download=this.fileName||"download",a.click()}},computed:{fileName(){return this.fileUrl.split("/").pop()}}};const Y=(0,v.A)(H,[["render",O],["__scopeId","data-v-3d14f40c"]]);var Z=Y,$={components:{Downloadlink:Z},props:{content:{type:String,required:!0},avatar:{type:String,required:!0},username:{type:String,required:!0},same:{type:Boolean,required:!0},type:{type:String,required:!0}},computed:{fileicon(){return this.same?o(370):o(879)}}};const aa=(0,v.A)($,[["render",M],["__scopeId","data-v-1d217532"]]);var ea=aa,oa={components:{ChatBubble:ea},setup(){const a=(0,r.WQ)("chat_window_text"),e=(0,r.WQ)("message-info"),o=(0,r.WQ)("login-info"),t=(0,r.EW)((()=>e.value)),l=a=>o.value.userId==a.senderID,s=(0,n.KR)(null),i=a=>{s.value&&(a.preventDefault(),s.value.scrollTop+=a.deltaY)};return(0,r.sV)((()=>{s.value&&(s.value.scrollTop=s.value.scrollHeight)})),{chat_window_text:a,items:t,scrollBox:s,handleScroll:i,isUser:l}}};const ta=(0,v.A)(oa,[["render",J],["__scopeId","data-v-195fe10a"]]);var ra=ta;function la(a,e,o,t,l,n){const s=(0,r.g2)("EmojiWindow");return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("button",{onClick:e[0]||(e[0]=(...a)=>n.toggleEmojiWindow&&n.toggleEmojiWindow(...a)),class:"button"},"🤗"),l.showEmojiWindow?((0,r.uX)(),(0,r.Wv)(s,{key:0,onSelectEmoji:n.selectEmoji},null,8,["onSelectEmoji"])):(0,r.Q3)("",!0)])}const na={class:"emoji-window"},sa=["onClick"];function ia(a,e,o,t,n,s){return(0,r.uX)(),(0,r.CE)("div",na,[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(n.emojis,(a=>((0,r.uX)(),(0,r.CE)("span",{key:a,onClick:e=>s.selectEmoji(a)},(0,l.v_)(a),9,sa)))),128))])}var ua={data(){return{emojis:["😀","😂","😍","😎","😢"]}},methods:{selectEmoji(a){this.$emit("select-emoji",a)}}};const da=(0,v.A)(ua,[["render",ia],["__scopeId","data-v-780bf290"]]);var ca=da,ma={components:{EmojiWindow:ca},data(){return{showEmojiWindow:!1}},methods:{toggleEmojiWindow(){this.showEmojiWindow=!this.showEmojiWindow},selectEmoji(a){this.$emit("send-emoji",a),this.showEmojiWindow=!1}},props:{}};const va=(0,v.A)(ma,[["render",la],["__scopeId","data-v-8c8277c6"]]);var Aa=va;function ga(a,e,o,l,n,s){return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.bo)((0,r.Lk)("textarea",{"onUpdate:modelValue":e[0]||(e[0]=a=>n.text=a),placeholder:"输入文本",class:"input-box",onKeydown:e[1]||(e[1]=(...a)=>s.handleKeydown&&s.handleKeydown(...a))}," ",544),[[t.Jo,n.text]]),(0,r.Lk)("button",{onClick:e[2]||(e[2]=(...a)=>s.sendText&&s.sendText(...a)),class:"button"},"发送(Enter)")])}var ha={setup(){const a=a=>new Promise((e=>setTimeout(e,a)));return{sleep:a}},data(){return{text:"",counter:1,coolDown:!1}},props:{emojiMessage:String},watch:{emojiMessage(a){a&&this.handleMessage()}},methods:{sendText(){this.text.length<=0||(this.$emit("send-text",this.text),this.text="")},handleKeydown(a){this.coolDown||(this.coolDown=!0,"Enter"!==a.key||a.ctrlKey?"Enter"===a.key&&a.ctrlKey&&(this.text+="\n"):(a.preventDefault(),this.text.length>0?this.sendText():alert("发送内容不能为空")),this.sleep(500),this.coolDown=!1)},handleMessage(){this.text+=this.emojiMessage,this.$emit("clearEmojiMessage")}}};const pa=(0,v.A)(ha,[["render",ga],["__scopeId","data-v-35585df3"]]);var fa=pa;function Ca(a,e,o,t,l,n){return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("button",{onClick:e[0]||(e[0]=(...a)=>n.clickUpload&&n.clickUpload(...a)),class:"button"},"📁"),(0,r.Lk)("input",{id:"file-upload",ref:"fileInput",type:"file",onChange:e[1]||(e[1]=(...a)=>n.uploadFile&&n.uploadFile(...a))},null,544)])}var Ia={name:"FileUploadButton",methods:{clickUpload(){this.$refs.fileInput.click()},uploadFile(a){const e=a.target.files[0];this.$emit("upload-file",e)}},props:{}};const ba=(0,v.A)(Ia,[["render",Ca],["__scopeId","data-v-df332b5e"]]);var ka=ba;const wa=a=>((0,r.Qi)("data-v-7de7f67c"),a=a(),(0,r.jt)(),a),ya={key:0,class:"chessAcceptWindow"},Ea=wa((()=>(0,r.Lk)("h3",null,"Game",-1))),Ba={style:{display:"flex",gap:"10px",position:"relative",left:"15%"}};function Qa(a,e,o,l,n,s){const i=(0,r.g2)("el-input"),u=(0,r.g2)("el-form-item"),d=(0,r.g2)("el-button"),c=(0,r.g2)("router-link"),m=(0,r.g2)("el-form");return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("button",{onClick:e[0]||(e[0]=(...a)=>s.playChess&&s.playChess(...a)),class:"button"},"♟️"),n.showChessAcceptWindow?((0,r.uX)(),(0,r.CE)("div",ya,[Ea,(0,r.bF)(m,{ref:"ruleFormRef",rules:l.rules,model:l.ruleForm,"label-width":"auto",style:{"max-width":"600px"}},{default:(0,r.k6)((()=>[(0,r.bF)(u,{label:"RoomID",prop:"roomID"},{default:(0,r.k6)((()=>[(0,r.bF)(i,{modelValue:l.ruleForm.roomID,"onUpdate:modelValue":e[1]||(e[1]=a=>l.ruleForm.roomID=a)},null,8,["modelValue"])])),_:1}),(0,r.bF)(u,{label:"游戏类型",prop:"type"},{default:(0,r.k6)((()=>[(0,r.bF)(i,{modelValue:l.ruleForm.type,"onUpdate:modelValue":e[2]||(e[2]=a=>l.ruleForm.type=a)},null,8,["modelValue"])])),_:1}),(0,r.Lk)("div",Ba,[(0,r.bF)(c,{target:"_blank",to:s.generateLink("create")},{default:(0,r.k6)((()=>[(0,r.bF)(d,{type:"warning",onClick:e[3]||(e[3]=a=>s.submitFormCreate(a,l.ruleFormRef))},{default:(0,r.k6)((()=>[(0,r.eW)("创建")])),_:1})])),_:1},8,["to"]),(0,r.bF)(c,{target:"_blank",to:s.generateLink("create")},{default:(0,r.k6)((()=>[(0,r.bF)(d,{type:"primary",onClick:e[4]||(e[4]=a=>s.submitFormLogin(a,l.ruleFormRef))},{default:(0,r.k6)((()=>[(0,r.eW)("加入")])),_:1})])),_:1},8,["to"]),(0,r.bF)(d,{onClick:(0,t.D$)(s.closeWindow,["prevent"])},{default:(0,r.k6)((()=>[(0,r.eW)("取消")])),_:1},8,["onClick"])])])),_:1},8,["rules","model"])])):(0,r.Q3)("",!0)])}var Fa={setup(){const a=(0,r.WQ)("login-info"),e=(0,r.EW)((()=>a.value?a.value:"")),o=(0,n.KR)(null),t=(0,n.KR)({roomID:"",type:""}),l={roomID:[{required:!0,message:"请输入ID",trigger:"blur"}],type:[{required:!0,message:"请输入假名",trigger:"blur"}]};return{curUser:e,ruleFormRef:o,ruleForm:t,rules:l,loginInfo:a}},data(){return{showChessAcceptWindow:!1}},methods:{generateLink(a){return{name:"ChessGame",query:{roomID:this.ruleForm.roomID,type:this.ruleForm.type,action:a,JWT:this.loginInfo.JWT,playerID:this.loginInfo.userId}}},playChess(){this.$emit("start-chess","chess"),this.showChessAcceptWindow=!0,this.broadcastRequest()},broadcastRequest(){},closeWindow(){this.showChessAcceptWindow=!1},submitFormCreate(a,e){e&&e.validate((e=>{e?(this.$emit("create-game",this.ruleForm.roomID,this.ruleForm.type),this.closeWindow()):(a.preventDefault(),alert("create failed!"))}))},submitFormLogin(a,e){e&&e.validate((e=>{e?(this.$emit("join-game",this.ruleForm.roomID),this.closeWindow()):(a.preventDefault(),alert("join the game failed!"))}))}}};const xa=(0,v.A)(Fa,[["render",Qa],["__scopeId","data-v-7de7f67c"]]);var La=xa;function Wa(a,e,o,t,l,n){return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("button",{onClick:e[0]||(e[0]=(...a)=>n.redirectToSnake&&n.redirectToSnake(...a)),class:"button"},"🐍")])}var Ra={name:"SnakeGameButton",methods:{redirectToSnake(){const a=window.open("","_blank");a?a.location.href="http://localhost:8082/":alert("请允许弹出窗口")}},props:{}};const Ka=(0,v.A)(Ra,[["render",Wa],["__scopeId","data-v-0b9ad9d5"]]);var Ta=Ka;const Sa={class:"input-window"},Ua={class:"topWindow"},Ga={class:"bottomWindow"},_a={name:"InputWindow",components:{EmojiButton:Aa,TextInput:fa,FileUploadButton:ka,ChessButton:La,SnakeGameButton:Ta},data(){return{text:"hello world",emojiMessage:""}},props:{inputWindowText:String},methods:{handleSendText(a){a.length<=0?alert("发送内容不能为空"):this.$emit("sendtext",a)},handleUploadFile(a){this.$emit("send-file",a)},handleSendEmoji(a){this.emojiMessage=a},handleChess(a){console.log("开始下棋:",a)},clearEmojiMessage(){this.emojiMessage=""},handleCreateGame(a,e){this.$emit("create-game",a,e)},handleJoinGame(a){this.$emit("join-game",a)}}};var ja=Object.assign(_a,{setup(a){return(a,e)=>((0,r.uX)(),(0,r.CE)("aside",Sa,[(0,r.Lk)("div",Ua,[(0,r.bF)(Aa,{onSendEmoji:a.handleSendEmoji},null,8,["onSendEmoji"]),(0,r.bF)(ka,{onUploadFile:a.handleUploadFile},null,8,["onUploadFile"]),(0,r.bF)(La,{onStartChess:a.handleChess,onCreateGame:a.handleCreateGame,onJoinGame:a.handleJoinGame},null,8,["onStartChess","onCreateGame","onJoinGame"]),(0,r.bF)(Ta)]),(0,r.Lk)("div",Ga,[(0,r.bF)(fa,{onSendText:a.handleSendText,emojiMessage:a.emojiMessage,onClearEmojiMessage:a.clearEmojiMessage},null,8,["onSendText","emojiMessage","onClearEmojiMessage"])])]))}});const Da=(0,v.A)(ja,[["__scopeId","data-v-a075dcae"]]);var Xa=Da,Ja=o(373),Na={__name:"AllEvent",setup(a,{expose:e}){const o=(0,r.WQ)("server-info"),t=()=>{},l=(a,e)=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/game/addgame",headers:{Authorization:s.value.JWT},params:{id:a,chatroom:A.value.currentRoomName,gameid:e}}).then((e=>{0===e.data.code?console.log("创建游戏成功",a):(console.log("创建游戏失败",a),alert(e.data.message))}),(a=>{console.log("创建游戏失败"),alert(a)}))},n=a=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/game/joingame",headers:{Authorization:s.value.JWT},params:{id:a}}).then((e=>{0===e.data.code?console.log("加入游戏成功",a):(console.log("加入游戏失败",a),alert(e.data.message))}),(a=>{console.log("加入游戏失败"),alert(a)}))},s=(0,r.WQ)("login-info"),i=()=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/user/register",data:{},params:{id:s.value.userId,fakename:s.value.fakeName,password:s.value.userPasswd}}).then((a=>{0===a.data.code?console.log("注册成功了",a):console.log("注册出现了错误")}),(a=>{console.log("注册出现了错误",response.data.message)}))},u=()=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/user/login",data:{},params:{id:s.value.userId,password:s.value.userPasswd}}).then((a=>{null!=a.data.data?(console.log("登录成功了"),s.value.JWT=a.data.data,s.value.isLogIn=!0,console.log("JWT: "+s.value.JWT)):console.log("登录出现了错误")}),(a=>{console.log("登录出现了错误"),console.log(a)}))},d=()=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/user/randomupdate",headers:{Authorization:s.value.JWT}}).then((a=>{0===a.data.code?console.log("更换用户头像成功成功了"):console.log("更换用户头像成功错误")}),(a=>{console.log("更换用户头像成功错误")}))},c=()=>{s.value.isLogIn=!1},m=a=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/room",headers:{Authorization:s.value.JWT},params:{roomName:a}}).then((e=>{console.log("创建房间成功"),console.log(a)}),(a=>{console.log("创建失败"),console.log(a)}))},v=(a,e)=>({roomName:a,roomAvatar:e,isLocked:null,isIn:null,password:null,messages:[]}),A=(0,r.WQ)("room-info"),g=a=>{(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/room/roominfo",headers:{Authorization:s.value.JWT,roomname:a},params:{password:"88888888"}}).then((a=>{}),(a=>{}))},h=()=>{s.value.isLogIn&&(0,Ja.A)({method:"get",url:o.value.serverList[0].ip+"/room",headers:{Authorization:s.value.JWT}}).then((a=>{if(null!=a.data&&null!=a.data.data)for(let e=0;e<a.data.data.length;e++){let t=a.data.data[e],r=t.roomName,l=o.value.serverList[0].ip+"/"+t.roompic;if(g(r),void 0===A.value.roomList.find((a=>a.roomName===r)))A.value.roomList.push(v(r,l)),console.log("添加了一个房间"),console.log(r);else{let a=A.value.roomList.findIndex((a=>a.roomName===r));A.value.roomList[a].roomAvatar=l}}}),(a=>{console.log("拉取所有房间失败")}))},p=a=>{let e=A.value.roomList.findIndex((e=>e.roomName===a)),t=A.value.roomList[e].roomAvatar;(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/room/randomupdate",headers:{Authorization:s.value.JWT,roomname:a}}).then((e=>{if(0===e.data.code){let e=A.value.roomList.findIndex((e=>e.roomName===a)),o=A.value.roomList[e].roomAvatar;console.log("更改房间头像成功, roomName:",a,t,o)}else console.log("更改房间头像失败, roomName:",a)}),(e=>{console.log("更改房间头像失败, roomName:",a)}))},f=a=>{s.value.isLogIn&&(0,Ja.A)({method:"post",url:o.value.serverList[0].ip+"/room/talk",params:{context:a},headers:{Authorization:s.value.JWT,roomname:A.value.currentRoomName}}).then((e=>{console.log("通过axios发送消息成功",a)}),(a=>{console.log("发送消息失败")}))},C=(a,e,t,r,l,n)=>({senderID:a,type:e,senderFakeName:t,content:r,avatar:o.value.serverList[0].ip+"/"+l,time:n}),I=(a,e)=>{try{let o=[];for(let a=0;a<e.length;a++){let t=e[a],r=t.senderid,l=t.context,n=t.sendername,s=t.senderpic,i=t.time,u=t.type;o.push(C(r,u,n,l,s,i))}let t=A.value.roomList.findIndex((e=>e.roomName===a));A.value.roomList[t].messages=o}catch(o){console.log("处理消息时出现错误",e)}},b=()=>{if(s.value.isLogIn)for(let a=0;a<A.value.roomList.length;a++){let e=A.value.roomList[a].roomName;(0,Ja.A)({method:"get",url:o.value.serverList[0].ip+"/room/talk",headers:{Authorization:s.value.JWT,roomname:e}}).then((a=>{I(e,a.data.data)}),(a=>{console.log("获取消息失败,roomname:",e)}))}},k=()=>{s.value.isLogIn&&(0,Ja.A)({method:"get",url:o.value.serverList[0].ip+"/user/userinfo",headers:{Authorization:s.value.JWT}}).then((a=>{s.value.headPhoto=o.value.serverList[0].ip+"/"+a.data.data.userpic}),(a=>{console.log("获取头像失败")}))},w=async a=>{console.log("File name:",a.name),console.log("File size:",a.size),console.log("File type:",a.type),console.log("Last modified:",a.lastModified);const e=new FormData;e.append("file",a);try{const a=await Ja.A.post(o.value.serverList[0].ip+"/room/talk/upload",e,{headers:{Authorization:s.value.JWT,roomName:A.value.currentRoomName}});console.log("File uploaded successfully",a.data)}catch(t){console.error("Error uploading file",t)}};let y,E,B;return(0,r.sV)((()=>{y=setInterval(h,1e3),E=setInterval(b,1e3),B=setInterval(k,1e3)})),(0,r.xo)((()=>{clearInterval(y),clearInterval(E),clearInterval(B)})),e({logIn:u,register:i,logOut:c,searchServer:t,createRoom:m,sendMessage:f,handleFileUpload:w,randomChangeAvatar:d,randomUpdataRoomAvatar:p,joinGame:n,createGame:l}),(a,e)=>null}};const za=Na;var Pa=za,Va=o(387);const Ma={key:0,id:"app"};var qa={__name:"App",setup(a){const e=(0,n.KR)("这里是侧边栏"),o=(0,n.KR)("这里是输入窗口"),t=(0,n.KR)("这里是显示消息窗口");(0,r.Gt)("side_bar_text",e),(0,r.Gt)("input_window_text",o),(0,r.Gt)("chat_window_text",t);const l=(0,Va.lq)(),s=(0,n.KR)(l.meta.showExtraContent),i=(0,n.KR)({isLogIn:!1,userId:"137",userPasswd:"sdsdsdf33",fakeName:"sss",headPhoto:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",JWT:null}),u=(0,n.KR)({currentRoomName:"sampleRoom1",roomList:[{roomName:"sampleRoom1",roomAvatar:"https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png",isLocked:!1,isIn:!0,password:"123456",messages:[{senderID:"137",type:"text",senderFakeName:"fakeSender",content:"hello1!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",avatar:"https://via.placeholder.com/40",time:"fakeTime"}]},{roomName:"sampleRoom2",roomAvatar:"https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png",isLocked:!1,isIn:!0,password:"123456",messages:[{senderID:"137",type:"text",senderFakeName:"fakeSender",content:"hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",avatar:"https://via.placeholder.com/40",time:"fakeTime"},{senderID:"138",type:"text",senderFakeName:"fakeSender",content:"hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",avatar:"https://via.placeholder.com/40",time:"fakeTime"},{senderID:"138",type:"text",senderFakeName:"fakeSender",content:"hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",avatar:"https://via.placeholder.com/40",time:"fakeTime"}]}]}),d=(0,n.KR)({serverList:[{ip:"http://127.0.0.1:8080"}]}),c=(0,r.EW)((()=>{const a=u.value.roomList.find((a=>a.roomName===u.value.currentRoomName));return a?a.messages:[]}));(0,r.Gt)("room-info",u),(0,r.Gt)("login-info",i),(0,r.Gt)("server-info",d),(0,r.Gt)("message-info",c);const m=(0,n.KR)(null),v=()=>{m.value&&m.value.logIn()},A=()=>{m.value&&m.value.register()},g=()=>{m.value&&m.value.logOut()},h=a=>{m.value.createRoom(a)},p=a=>{m.value.sendMessage(a)},f=a=>{m.value.handleFileUpload(a)},C=()=>{m.value.randomChangeAvatar()},I=a=>{m.value.randomUpdataRoomAvatar(a)},b=(a,e)=>{m.value.createGame(a,e)},k=a=>{m.value.joinGame(a)};return(a,e)=>{const o=(0,r.g2)("router-view"),t=(0,r.g2)("functions");return(0,r.uX)(),(0,r.CE)(r.FK,null,[(0,r.bF)(o),s.value?((0,r.uX)(),(0,r.CE)("div",Ma,[(0,r.Lk)("header",null,[(0,r.bF)(L,{onLogIn:v,onRegister:A,onLogOut:g,onCreateroom:h,onRandomChangeUserAvatar:C,id:"top-bar"})]),(0,r.Lk)("main",null,[(0,r.bF)(D,{onRandomUpdateRoomAvatar:I}),(0,r.bF)(ra),(0,r.bF)(Xa,{onSendtext:p,onSendFile:f,onCreateGame:b,onJoinGame:k})]),(0,r.bF)(t,null,{default:(0,r.k6)((()=>[(0,r.bF)(Pa,{ref_key:"allEvent",ref:m},null,512)])),_:1})])):(0,r.Q3)("",!0)],64)}}};const Oa=qa;var Ha=Oa;const Ya=a=>((0,r.Qi)("data-v-d4efc94c"),a=a(),(0,r.jt)(),a),Za={class:"gobang"},$a=Ya((()=>(0,r.Lk)("canvas",{id:"gobang",width:"900",height:"600"},null,-1))),ae=[$a];function ee(a,e,o,t,l,n){return(0,r.uX)(),(0,r.CE)("div",Za,ae)}var oe={name:"ChessGame",setup(){const a=(0,n.KR)({JWT:null,playerID:null}),e=(0,r.WQ)("server-info"),o=(0,n.KR)({gameId:"id1",gameType:"1",roomName:"room1",isIn:!0,chessBoard:[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],chessBoardHeight:15,chessBoardWidth:15,turntoId:"id1",whiteTurn:!1,isOver:!1,winnerId:"id1"});return{curGame:o,playerInfo:a,serverInfo:e}},data(){return{ctx:null,resultArr:[]}},mounted(){this.curGame.gameId=this.$route.query.roomID,this.curGame.gameType=this.$route.query.type,this.playerInfo.JWT=this.$route.query.JWT,this.playerInfo.playerID=this.$route.query.playerID,console.log("启动：",this.curGame.gameId,this.curGame.gameType),console.log("JWT",this.playerInfo.JWT),console.log("playerID",this.playerInfo.playerID);let a=this,e=document.getElementById("gobang");e.addEventListener("click",a.handleClick),a.ctx=e.getContext("2d"),a.ctx.translate(70,70);for(var o=0;o<this.curGame.chessBoardHeight;o++)a.resultArr.push(new Array(this.curGame.chessBoardWidth).fill(0));a.drawCheckerboard(),console.log("准备好了，可以开始下棋了"),this.intervalId=setInterval(this.listenOtherChessLocation,300)},computed:{chessText(){return this.curGame.whiteTurn?"白棋":"黑棋"}},methods:{drawCheckerboard(){let a=this;a.ctx.beginPath(),a.ctx.fillStyle="#fff",a.ctx.rect(0,0,30*this.curGame.chessBoardHeight,30*this.curGame.chessBoardWidth),a.ctx.fill();for(var e=0;e<this.curGame.chessBoardHeight;e++){a.ctx.beginPath(),a.ctx.strokeStyle="#D6D1D1",a.ctx.moveTo(15+30*e,15),a.ctx.lineTo(15+30*e,29*this.curGame.chessBoardHeight),a.ctx.stroke(),a.ctx.moveTo(15,15+30*e),a.ctx.lineTo(29*this.curGame.chessBoardWidth,15+30*e),a.ctx.stroke();for(var o=0;o<this.curGame.chessBoardWidth;o++)a.resultArr[e][o]=this.curGame.chessBoard[e*this.curGame.chessBoardWidth+o],this.drawChess(e,o)}a.drawText()},drawChess(a,e){let o=this,t=a,r=e;if(0==o.resultArr[t][r])return;let l=o.ctx.createRadialGradient(30*t+15,30*r+15,4,30*t+15,30*r+15,10);l.addColorStop(0,-1==o.resultArr[t][r]?"#fff":"#4c4c4c"),l.addColorStop(1,-1==o.resultArr[t][r]?"#dadada":"#000"),o.ctx.beginPath(),o.ctx.fillStyle=l,o.ctx.arc(30*t+15,30*r+15,10,0,2*Math.PI,!1),o.ctx.fill(),o.ctx.closePath()},drawText(){let a=this;a.ctx.clearRect(29*this.curGame.chessBoardWidth+60,0,300,70),a.ctx.fillStyle="#fff",a.ctx.font="20px Arial";const e=this.curGame.turntoId;a.ctx.fillText("本轮："+e+"("+a.chessText+")",29*this.curGame.chessBoardWidth+70,35)},drawResult(){let a=this;a.ctx.fillStyle="#ff2424",a.ctx.font="20px Arial";const e=this.curGame.winnerId;a.ctx.fillText(e+" ( "+a.chessText+" ) 胜！",29*this.curGame.chessBoardWidth+70,70),document.getElementById("gobang").removeEventListener("click",a.handleClick)},handleClick(a){let e=a.offsetX-70,o=a.offsetY-70;if(e<15||e>435||o<15||o>435)return;let t=Math.round((e-15)/30),r=Math.round((o-15)/30);0===this.resultArr[t][r]&&this.tellChessLocation(t,r)},tellChessLocation(a,e){console.log("尝试落子",a,e),(0,Ja.A)({method:"post",url:this.serverInfo.serverList[0].ip+"/game/play",headers:{Authorization:this.playerInfo.JWT},params:{id:this.curGame.gameId,row:e,col:a}}).then((a=>{0===a.data.code?console.log("落子成功"):(console.log("落子失败"),alert(a.data.message))}),(a=>{console.log("落子失败"),alert(a)}))},parseBoard(a){try{let e=JSON.parse(a),o=e.length,t=e[0].length,r=[];for(let a=0;a<t;a++)for(let t=0;t<o;t++){let o=e[t][a];-1===o&&r.push(0),0===o&&r.push(-1),1===o&&r.push(1)}return r}catch(e){return console.error("Error parsing board string:",e),null}},splitStringByComma(a){return a.split(",")},listenOtherChessLocation(){(0,Ja.A)({method:"get",url:this.serverInfo.serverList[0].ip+"/game/showgame",headers:{Authorization:this.playerInfo.JWT},params:{id:this.curGame.gameId}}).then((a=>{if(0===a.data.code){let e=a.data.data;this.curGame.chessBoard=this.parseBoard(e.board),this.drawCheckerboard(),this.curGame.turntoId=this.splitStringByComma(e.players)[e.turn],this.curGame.isOver=-1!==e.winner,this.curGame.winnerId=-1===e.winner?null:this.splitStringByComma(e.players)[e.winner],this.curGame.isOver&&this.drawResult()}else console.log("获取棋面失败",this.curGame.gameId,a.data.message),alert(a.data.message)}),(a=>{console.log("获取棋面失败"),alert(a)}))}}};const te=(0,v.A)(oe,[["render",ee],["__scopeId","data-v-d4efc94c"]]);var re=te;const le=[{path:"/",name:"App",component:Ha,meta:{showExtraContent:!0},children:[{path:"ChessGame",name:"ChessGame",component:re,meta:{showExtraContent:!1}},{path:"external",redirect:"https://www.baidu.com/?tn=68018901_16_pg"}]}],ne=(0,Va.aE)({history:(0,Va.LA)(""),routes:le});var se=ne,ie=o(147);o(188);const ue=(0,t.Ef)(Ha);ue.use(ie.A),ue.use(se).mount("#app")},879:function(a){a.exports="data:image/png;base64,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"},370:function(a){a.exports="data:image/png;base64,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"}},e={};function o(t){var r=e[t];if(void 0!==r)return r.exports;var l=e[t]={exports:{}};return a[t].call(l.exports,l,l.exports,o),l.exports}o.m=a,function(){var a=[];o.O=function(e,t,r,l){if(!t){var n=1/0;for(d=0;d<a.length;d++){t=a[d][0],r=a[d][1],l=a[d][2];for(var s=!0,i=0;i<t.length;i++)(!1&l||n>=l)&&Object.keys(o.O).every((function(a){return o.O[a](t[i])}))?t.splice(i--,1):(s=!1,l<n&&(n=l));if(s){a.splice(d--,1);var u=r();void 0!==u&&(e=u)}}return e}l=l||0;for(var d=a.length;d>0&&a[d-1][2]>l;d--)a[d]=a[d-1];a[d]=[t,r,l]}}(),function(){o.d=function(a,e){for(var t in e)o.o(e,t)&&!o.o(a,t)&&Object.defineProperty(a,t,{enumerable:!0,get:e[t]})}}(),function(){o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(a){if("object"===typeof window)return window}}()}(),function(){o.o=function(a,e){return Object.prototype.hasOwnProperty.call(a,e)}}(),function(){o.r=function(a){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(a,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(a,"__esModule",{value:!0})}}(),function(){var a={524:0};o.O.j=function(e){return 0===a[e]};var e=function(e,t){var r,l,n=t[0],s=t[1],i=t[2],u=0;if(n.some((function(e){return 0!==a[e]}))){for(r in s)o.o(s,r)&&(o.m[r]=s[r]);if(i)var d=i(o)}for(e&&e(t);u<n.length;u++)l=n[u],o.o(a,l)&&a[l]&&a[l][0](),a[l]=0;return o.O(d)},t=self["webpackChunkserver_side_application"]=self["webpackChunkserver_side_application"]||[];t.forEach(e.bind(null,0)),t.push=e.bind(null,t.push.bind(t))}();var t=o.O(void 0,[504],(function(){return o(499)}));t=o.O(t)})();
//# sourceMappingURL=app.30bf23e7.js.map