<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Do not use this for real payments. Demo validation only. --%>
<!doctype html>
<html>
<head>
<style>
/* payment.css - demo styling */
body{
    font-family: Arial, sans-serif;
    background: #f5f7fb;
    padding: 30px;
}
.card-wrap{
    max-width:720px;
    margin: 0 auto;
    background: #fff;
    padding: 24px;
    border-radius: 8px;
    box-shadow: 0 6px 18px rgba(0,0,0,0.06);
}
h2{ margin-top:0; color:#222; font-size:20px; display:flex; justify-content:space-between; align-items:center;}
.field{ margin-bottom:14px; }
.field label{ display:block; color:#444; margin-bottom:6px; font-weight:600; font-size:13px;}
input[type="text"], input[type="password"], input[type="tel"], input[type="text"]{
    width:100%;
    padding:10px 12px;
    border:1px solid #ddd;
    border-radius:4px;
    font-size:14px;
    box-sizing:border-box;
}
.row{ display:flex; gap:12px; }
.col{ flex:1; min-width:120px; }
.error-text{
    color:#d43434;
    margin-top:6px;
    font-size:13px;
    min-height:18px;
}
input:invalid { outline: none; border-color:#e0a0a0; }
.primary{
    background:#0072ce;
    color:white;
    border:none;
    padding:10px 16px;
    border-radius:6px;
    font-weight:600;
    cursor:pointer;
}
.primary:hover{ opacity:0.95; }
</style>
    <meta charset="utf-8">
    <title>Pay with card</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css">
    <script>
        // Simple client-side validation (mirrors server-side checks for UX).
        function digitsOnly(s){ return s.replace(/\D/g,''); }
        function luhnCheck(cardNumber){
            var digits = digitsOnly(cardNumber);
            var sum = 0;
            var alt = false;
            for (var i = digits.length - 1; i >= 0; i--) {
                var n = parseInt(digits.charAt(i), 10);
                if (alt) {
                    n *= 2;
                    if (n > 9) n -= 9;
                }
                sum += n;
                alt = !alt;
            }
            return (sum % 10) === 0;
        }

        function cardType(number) {
            var d = digitsOnly(number);
            if (/^3[47]/.test(d)) return "AMEX";
            if (/^4/.test(d)) return "VISA";
            if (/^5[1-5]/.test(d)) return "MASTERCARD";
            if (/^6/.test(d)) return "DISCOVER";
            return "UNKNOWN";
        }

        function validateForm(e) {
            var card = document.getElementById('cardNumber').value.trim();
            var exp = document.getElementById('expiry').value.trim();
            var cvv = document.getElementById('cvv').value.trim();
            var postal = document.getElementById('postal').value.trim();

            // Clear previous errors
            var errEls = document.querySelectorAll('.error-text'); for(var i=0;i<errEls.length;i++){ errEls[i].textContent='';}
            var valid = true;

            var cd = card.replace(/\s+/g,'');

            if (cd.length < 13 || cd.length > 19 || !/^\d+$/.test(cd) || !luhnCheck(cd)) {
                document.getElementById('cardError').textContent = 'This card number is not valid.';
                valid = false;
            }

            // Expiry simple check MM/YY
            if (!/^\d{2}\/\d{2}$/.test(exp)) {
                document.getElementById('expError').textContent = 'This expiration date is not valid.';
                valid = false;
            } else {
                var parts = exp.split('/');
                var mm = parseInt(parts[0],10);
                var yy = parseInt(parts[1],10);
                if (mm < 1 || mm > 12) {
                    document.getElementById('expError').textContent = 'Invalid month.';
                    valid = false;
                } else {
                    // build YearMonth
                    var now = new Date();
                    var curYY = now.getFullYear() % 100;
                    var curMM = now.getMonth() + 1;
                    if (yy < curYY || (yy === curYY && mm < curMM)) {
                        document.getElementById('expError').textContent = 'Card expired.';
                        valid = false;
                    }
                }
            }

            var type = cardType(cd);
            if (type === "AMEX") {
                if (!/^\d{4}$/.test(cvv)) {
                    document.getElementById('cvvError').textContent = 'AMEX CVV must be 4 digits.';
                    valid = false;
                }
            } else {
                if (!/^\d{3}$/.test(cvv)) {
                    document.getElementById('cvvError').textContent = 'CVV must be 3 digits.';
                    valid = false;
                }
            }

            if (postal.length === 0) {
                document.getElementById('postalError').textContent = 'Postal code required.';
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
            }
        }

        window.addEventListener('load', function(){
            document.getElementById('paymentForm').addEventListener('submit', validateForm);
            // simple formatting for card number
            document.getElementById('cardNumber').addEventListener('input', function(e){
                var v = this.value.replace(/\D/g,'');
                // group as 4 4 4 4...
                var groups = v.match(/.{1,4}/g);
                this.value = groups ? groups.join(' ') : '';
            });
        });
    </script>
</head>
<body>
<div class="card-wrap">
    <h2>Pay with card</h2>

    <form action="${pageContext.request.contextPath}/Payment" method="post">

        <div class="field">
            <label>Card Number</label>
            <input id="cardNumber" name="cardNumber" value="${param.cardNumber != null ? param.cardNumber : ''}" placeholder="0000 0000 0000 0000" />
            <div id="cardError" class="error-text">
                <%= request.getAttribute("cardError") != null ? request.getAttribute("cardError") : "" %>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label>Expiration Date (MM/YY)</label>
                <input id="expiry" name="expiry" value="${param.expiry != null ? param.expiry : ''}" placeholder="MM/YY" />
                <div id="expError" class="error-text">
                    <%= request.getAttribute("expiryError") != null ? request.getAttribute("expiryError") : "" %>
                </div>
            </div>
            <div class="col">
                <label>CVV</label>
                <input id="cvv" name="cvv" value="${param.cvv != null ? param.cvv : ''}" placeholder="123" />
                <div id="cvvError" class="error-text">
                    <%= request.getAttribute("cvvError") != null ? request.getAttribute("cvvError") : "" %>
                </div>
            </div>
            <div class="col">
                <label>Postal Code</label>
                <input id="postal" name="postal" value="${param.postal != null ? param.postal : ''}" placeholder="Postal code" />
                <div id="postalError" class="error-text">
                    <%= request.getAttribute("postalError") != null ? request.getAttribute("postalError") : "" %>
                </div>
            </div>
        </div>

        <div style="text-align:right; margin-top:18px">
            <button type="submit" class="primary">Pay</button>
        </div>

        <% if (request.getAttribute("generalError") != null) { %>
            <div class="error-text" style="margin-top:12px"><%= request.getAttribute("generalError") %></div>
        <% } %>
    </form>
</div>
</body>
</html>
