$wnd.edu_utc_atc_widgetset_ArrivaltimecalculatorWidgetset.runAsyncCallback7("function NAc(){}\nfunction PAc(){}\nfunction xTd(){zPd.call(this)}\nfunction Btb(a,b){this.a=b;this.b=a}\nfunction Zsb(a,b){Irb(a,b);--a.b}\nfunction Zl(a){return (hk(),a).createElement('col')}\nfunction F8c(){ZTb.call(this);this.a=_z(BRb(tcb,this),2634)}\nfunction Y8c(){qpb.call(this);this.d=1;this.a=1;this.c=false;npb(this,V8c(this),0,0)}\nfunction X8c(a,b,c){a.d=b;a.a=c;opb(a,a.b);npb(a,V8c(a),0,0)}\nfunction Bqc(a,b,c){CRb(a.a,new TAc(new jBc(tcb),Sie),pz(hz(cgb,1),ghe,1,5,[o0d(b),o0d(c)]))}\nfunction V8c(a){a.b=new atb(a.d,a.a);bob(a.b,NAe);Vnb(a.b,NAe);vob(a.b,a,(ht(),ht(),gt));return a.b}\nfunction Brb(a,b){var c,d,e;e=Erb(a,b.c);if(!e){return null}d=nk((hk(),e)).sectionRowIndex;c=e.cellIndex;return new Btb(d,c)}\nfunction atb(a,b){Orb.call(this);Jrb(this,new esb(this));Mrb(this,new Jtb(this));Krb(this,new Etb(this));$sb(this,b);_sb(this,a)}\nfunction Ysb(a,b){if(b<0){throw Pib(new B$d('Cannot access a row with a negative index: '+b))}if(b>=a.b){throw Pib(new B$d(gme+b+hme+a.b))}}\nfunction _sb(a,b){if(a.b==b){return}if(b<0){throw Pib(new B$d('Cannot set number of rows to '+b))}if(a.b<b){btb((ilb(),a.M),b-a.b,a.a);a.b=b}else{while(a.b>b){Zsb(a,a.b-1)}}}\nfunction Dtb(a,b,c){var d,e;b=$wnd.Math.max(b,1);e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){hj(a.a,Zl($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){qj(a.a,a.a.lastChild)}}}\nfunction Erb(a,b){var c,d,e;d=(ilb(),(hk(),gk).qc(b));for(;d;d=(null,nk(d))){if(U0d(Hj(d,'tagName'),'td')){e=(null,nk(d));c=(null,nk(e));if(c==a.M){return d}}if(d==a.M){return null}}return null}\nfunction W8c(a,b,c,d){var e,f;if(b!=null&&c!=null&&d!=null){if(b.length==c.length&&c.length==d.length){for(e=0;e<b.length;e++){f=asb(a.b.N,R$d(c[e],10),R$d(d[e],10));f.style[GFe]=b[e]}}a.c=true}}\nfunction btb(a,b,c){var d=$doc.createElement('td');d.innerHTML=joe;var e=$doc.createElement(Uie);for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction $sb(a,b){var c,d,e,f,g,h,j;if(a.a==b){return}if(b<0){throw Pib(new B$d('Cannot set number of columns to '+b))}if(a.a>b){for(c=0;c<a.b;c++){for(d=a.a-1;d>=b;d--){xrb(a,c,d);e=zrb(a,c,d,false);f=Gtb(a.M,c);f.removeChild(e)}}}else{for(c=0;c<a.b;c++){for(d=a.a;d<b;d++){g=Gtb(a.M,c);h=(j=(ilb(),tm($doc)),j.innerHTML=joe,ilb(),j);glb.Pd(g,wlb(h),d)}}}a.a=b;Dtb(a.O,b,false)}\nfunction JAc(c){var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.nk(ucb,$Fe,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.nk(ucb,_Fe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.nk(ucb,aGe,d);var d={setter:function(a,b){a.d=b.ip()},getter:function(a){return o0d(a.d)}};c.nk(ucb,bGe,d);var d={setter:function(a,b){a.e=b.ip()},getter:function(a){return o0d(a.e)}};c.nk(ucb,cGe,d)}\nvar $Fe='changedColor',_Fe='changedX',aGe='changedY',bGe='columnCount',cGe='rowCount';pjb(827,794,ime,atb);_.Ie=function ctb(a){return this.a};_.Je=function dtb(){return this.b};_.Ke=function etb(a,b){Ysb(this,a);if(b<0){throw Pib(new B$d('Cannot access a column with a negative index: '+b))}if(b>=this.a){throw Pib(new B$d(eme+b+fme+this.a))}};_.Le=function ftb(a){Ysb(this,a)};_.a=0;_.b=0;var UG=v_d(Ule,'Grid',827,$G);pjb(2183,1,{},Btb);_.a=0;_.b=0;var XG=v_d(Ule,'HTMLTable/Cell',2183,cgb);pjb(1928,1,jne);_.$b=function MAc(){CBc(this.b,ucb,cbb);rBc(this.b,Dse,w3);sBc(this.b,w3,new NAc);sBc(this.b,ucb,new PAc);ABc(this.b,w3,Qne,new jBc(ucb));JAc(this.b);yBc(this.b,ucb,$Fe,new jBc(hz(igb,1)));yBc(this.b,ucb,_Fe,new jBc(hz(igb,1)));yBc(this.b,ucb,aGe,new jBc(hz(igb,1)));yBc(this.b,ucb,bGe,new jBc(Xfb));yBc(this.b,ucb,cGe,new jBc(Xfb));pBc(this.b,w3,new ZAc(p$,Fse,pz(hz(igb,1),hhe,2,6,[poe,Gse])));pBc(this.b,w3,new ZAc(p$,Hse,pz(hz(igb,1),hhe,2,6,[Ise])));ddc((!Xcc&&(Xcc=new ldc),Xcc),this.a.d)};pjb(1930,1,Hye,NAc);_.fk=function OAc(a,b){return new F8c};var IZ=v_d(Mqe,'ConnectorBundleLoaderImpl/7/1/1',1930,cgb);pjb(1931,1,Hye,PAc);_.fk=function QAc(a,b){return new xTd};var JZ=v_d(Mqe,'ConnectorBundleLoaderImpl/7/1/2',1931,cgb);pjb(1929,34,HFe,F8c);_.eg=function H8c(){return !this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)};_.fg=function I8c(){return !this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)};_.vg=function J8c(){return !this.G&&(this.G=new Y8c),_z(this.G,223)};_.Hh=function G8c(){return new Y8c};_.Og=function K8c(){vob((!this.G&&(this.G=new Y8c),_z(this.G,223)),this,(ht(),ht(),gt))};_.Oc=function L8c(a){Bqc(this.a,(!this.G&&(this.G=new Y8c),_z(this.G,223)).e,(!this.G&&(this.G=new Y8c),_z(this.G,223)).f)};_.Dg=function M8c(a){RTb(this,a);(a.uh(cGe)||a.uh(bGe)||a.uh('updateGrid'))&&X8c((!this.G&&(this.G=new Y8c),_z(this.G,223)),(!this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)).e,(!this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)).d);if(a.uh(_Fe)||a.uh(aGe)||a.uh($Fe)||a.uh('updateColor')){W8c((!this.G&&(this.G=new Y8c),_z(this.G,223)),(!this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)).a,(!this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)).b,(!this.P&&(this.P=pFb(this)),_z(_z(this.P,6),360)).c);(!this.G&&(this.G=new Y8c),_z(this.G,223)).c||CRb(this.a.a,new TAc(new jBc(tcb),'refresh'),pz(hz(cgb,1),ghe,1,5,[]))}};var w3=v_d(IFe,'ColorPickerGridConnector',1929,p$);pjb(223,503,{52:1,58:1,21:1,8:1,19:1,20:1,18:1,37:1,40:1,22:1,39:1,17:1,14:1,223:1,26:1},Y8c);_.Tc=function Z8c(a){return vob(this,a,(ht(),ht(),gt))};_.Oc=function $8c(a){var b;b=Brb(this.b,a);if(!b){return}this.f=b.b;this.e=b.a};_.a=0;_.c=false;_.d=0;_.e=0;_.f=0;var y3=v_d(IFe,'VColorPickerGrid',223,tG);pjb(360,12,{6:1,12:1,30:1,105:1,360:1,3:1},xTd);_.d=0;_.e=0;var ucb=v_d(Rye,'ColorPickerGridState',360,cbb);Vge(Dh)(7);\n//# sourceURL=edu.utc.atc.widgetset.ArrivaltimecalculatorWidgetset-7.js\n")
