<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic Page Needs -->
        <meta charset="utf-8">

        <link href="http://www.iamport.kr/" rel="canonical">
        
 
</header>


            <!-- Content -->
                        <!-- Content -->
<section style="background-color: #18bc9c">
	<div class="container" style="padding: 50px 0 50px 0;">
		<h1>I'mport; 결제 모듈 DEMO</h1>
		<div id="demo" class="col-md-8 col-md-offset-1 col-xs-11">
			<form name="frm_payment" id="frm_payment" class="form-horizontal">
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="pg_provider" class="col-md-4 col-xs-4">지원 PG사</label>
                    <select name="pg_provider" id="pg-provider" class="col-md-8 col-xs-8">
                        <option value="kakao" data-option="card" selected>카카오페이</option>
                        <option value="html5_inicis" data-option="card,trans,vbank,phone">KG이니시스(웹표준결제)</option>
                        <option value="inicis" data-option="card,trans,vbank,phone">KG이니시스(기존모듈)</option>
                        <option value="uplus" data-option="card,trans,vbank,phone">LG유플러스</option>
                        <option value="nice" data-option="card,trans,vbank,phone">나이스정보통신</option>
                        <option value="jtnet" data-option="card,trans,vbank,phone">JTNet</option>
                        <option value="danal" data-option="phone">다날-휴대폰소액결제전용</option>
                        <option value="paypal" data-option="card">페이팔-ExpressCheckout</option>
                    </select>
                </div>
                <div class="form-group">
                    <p id="pay_method_help" class="col-md-8 col-md-offset-4 col-xs-11 col-xs-offset-1">
                        실제 승인이 이루어진 테스트 결제건은 30분이내로 카카오페이에서 자동 취소처리 합니다.
                    </p>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="pay_method" class="col-md-4 col-xs-4">결제수단</label>

                    <select name="pay_method" id="pay_method" class="col-md-8 col-xs-8">
				        <option value="card">신용카드</option>
				    </select>
                </div>
                <div class="form-group">
                    <div class="checkbox col-md-4 col-md-offset-3" style="padding: 0px;">
                        <label>
                            <input type="checkbox" name="use_escrow" value="escrow" id="use_escrow">
                            <span id="escrow-label"> 에스크로결제적용</span>
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="merchant_uid" class="col-md-4 col-xs-4">주문번호</label>
					<input type="text" name="merchant_uid" id="merchant_uid" value="" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
					<label for="name" class="col-md-4 col-xs-4">결제명</label>
					<input type="text" name="name" id="name" value="결제테스트" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
					<label for="amount" class="col-md-4 col-xs-4">금액</label>
					<input type="tel" name="amount" id="amount" value="1004" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
                    <label for="buyer_email" class="col-md-4 col-xs-4">이메일주소</label>
					<input type="text" name="buyer_email" id="buyer_email" value="iamport@siot.do" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
					<label for="buyer_name" class="col-md-4 col-xs-4">성함</label>
					<input type="text" name="buyer_name" id="buyer_name" value="구매자" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
					<label for="buyer_tel" class="col-md-4 col-xs-4">전화번호</label>
					<input type="tel" name="buyer_tel" id="buyer_tel" value="010-1234-5678" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
					<label for="buyer_addr" class="col-md-4 col-xs-4">주소</label>
					<input type="text" name="buyer_addr" id="buyer_addr" value="서울특별시 강남구 삼성동" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
                    <label for="buyer_postcode" class="col-md-4 col-xs-4">우편번호</label>
					<input type="text" name="buyer_postcode" id="buyer_postcode" value="123-456" class="col-md-8 col-xs-8"/>
                </div>
                <div class="form-group">
                    <label for="vbank_due" class="col-md-4 col-xs-4">가상계좌 입금일자<br>(YYYYMMDD)</label>
					<input type="text" name="vbank_due" id="vbank_due" value="" class="col-md-8 col-xs-8"/>
                </div>

                <div class="form-group">
                    <label for="in_app" class="col-md-4 col-xs-4"></label>
                    <label for="in_app" class="col-md-8 col-xs-8" style="text-align:left">
                        <input type="checkbox" name="in_app" value="in_app" id="in_app">
                        <span> 앱내 webView를 통한 결제인 경우만 체크</span>
                    </label>
                </div>
			</form>
			<div id="responser"></div>
			<a id="requester" class="btn btn-primary">결제하기</a>
		</div>
	</div>
</section>
            <!-- End Content -->

            <!--- Footer -->
            <footer>
	<div class="rst-footer-menu">
		<div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 snsicon">
                    <a href="https://ko.wordpress.org/plugins/iamport-for-woocommerce/" target="_blank" onclick="ga('send', 'event', 'outbound', '우커머스용 아임포트 플러그인');">
                        <img alt="woocommerce icon" src="/images/woo-icon-white.png">
                    </a>
                    <a href="https://ko.wordpress.org/plugins/iamport-payment/" target="_blank" onclick="ga('send', 'event', 'outbound', '결제버튼 생성 플러그인');">
                       <i class="fa fa-wordpress"></i>
                    </a>
                    <a href="https://www.facebook.com/iamportservice?_rdr=p" target="_blank"
                       onclick="ga('send', 'event', 'outbound', '페이스북 링크');">
                        <i class="fa fa-facebook"></i>
                    </a>
                </div>
                <div class="col-xs-12 col-sm-6 text-left facebooklike">
                    <div id="fb-root"></div>
                    <div class="fb-like" data-href="https://www.facebook.com/iamportservice" data-layout="standard" data-action="like" data-show-faces="true" data-share="true" data-colorscheme="dark"></div>
               </div>
               <div style="clear:both;"></div>
           </div>
           <div class="row">
               <div class="col-xs-12">
                    <ul class="companyinfo">
                        <li class="companylabel">상호명</li>
                        <li>(주)시옷</li>
                        <li class="companylabel">대표</li>
                        <li>장지윤</li>
                        <li class="companylabel">개인정보담당자</li>
                        <li>장지윤</li>
                        <li class="companylabel">사업자등록번호</li>
                        <li>117-81-78260</li>
                        <li class="hidden-xs" style="display:block;"></li>
                        <li class="companylabel">사업장소재지</li>
                        <li>서울시 영등포구 국제금융로 10 Three IFC 19층</li>
                        <li class="companylabel">Tel</li>
                        <li><a href="tel:1670-5176">1670-5176 (운영시간 : 13:00~19:00)</a></li>
                        <li class="companylabel">Email</li>
                        <li><a href="mailto:iamport@siot.do">iamport@siot.do</a></li>
                        <li class="hidden-xs" style="display:block;"></li>
						<!-- <li class="companylabel">아임포트 워드프레스 플러그인 사용안내</li>
                        <li><a href="https://doc.co/4W9pSG" target="_blank">[다운로드]</a></li>
                        <li class="companylabel">아임포트 소개서 영문버전</li>
                        <li><a href="https://doc.co/4r5Kww" target="_blank">[다운로드]</a></li> -->
                        <li class="hidden-xs" style="display:block;"></li>
                        <li><a href="/faq" style="color:#ffff00">※ 8월 5일(금) 아임포트 고객센터 휴무안내 [자세히보기]</a></li>
                        <li class="hidden-xs" style="display:block;"></li>
                        <li class="companylabel">© 2014 ~ 2016 SIOT. All right reserved.</li>
                    </ul>
               </div>
           </div>
		</div>
	</div>
</footer>
            <!--- End Footer -->
        </section>
        <!--- End Wrapper -->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.1.11.1.js"></script>

        <!-- Bootstrap Js Compiled Plugins -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/bootstrap.min.js"></script>

        <!-- WoW Js -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/wow.min.js"></script>

        <!-- Add Fancybox -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/fancybox/jquery.fancybox.js?v=2.1.5"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/fancybox/helpers/jquery.fancybox-media.js?v=1.0.6"></script>

        <!-- Owl Slider Js -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/owl.carousel.js"></script>

        <!-- Custom Js -->
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jqBootstrapValidation.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.ddslick.min.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/custom-form-elements.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/countrySelect.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.countdown.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/circle-progress.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.appear.js"></script>
        <script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/main.js"></script>

        <!-- Angular.js -->
                <script type="text/javascript" src="/js/moment.min.js"></script><script type="text/javascript" src="/js/demo.js"></script>
        <script>
            // Google Analytics
            (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
            })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

            ga('create', 'UA-53976410-1', 'auto');
            ga('send', 'pageview');

            // Google CSE code snippet
            (function() {
                // Custom Search Engine ID
                var cx = '017524840236596159276:4u23l5rfvii';
                var gcse = document.createElement('script');
                gcse.type = 'text/javascript';
                gcse.async = true;
                gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
                           '//cse.google.com/cse.js?cx=' + cx;
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(gcse, s);
            })();

            // Facebook Pixel Code
            !function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?
            n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;
            n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;
            t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,
            document,'script','//connect.facebook.net/en_US/fbevents.js');

            fbq('init', '781773038618579');
            fbq('track', "PageView");

            // Facebook API
            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) return;
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v2.5&appId=1655157841399316";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <script>
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-53976410-1']);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www')
                              + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
        <noscript>
            <img height="1" width="1" style="display:none"
            src="https://www.facebook.com/tr?id=781773038618579&ev=PageView&noscript=1"/>
        </noscript>
    </body>
</html>
