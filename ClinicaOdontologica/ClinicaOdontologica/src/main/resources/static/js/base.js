function crearNavbar() {
  return `
  <div class="navbar bg-base-100">
        <div class="navbar-start">
            <a class="btn btn-neutral md:btn-md lg:btn-lg" href="index.html">Consultorio <br />
                Odontologico</a>
        </div>
        <div class="navbar-center hidden sm:flex gap-4">
            <div class="dropdown dropdown-hover">
                <div tabindex="0" role="button" class="btn btn-secondary m-1">
                Pacientes
                <span class="rotate-90">
                        &#10097;
                    </span>
                </div>
                <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-neutral rounded-box w-52 text-neutral-content">
                    <li><a href="post_pacientes.html">Agregar</a></li>
                    <li><a href="get_pacientes.html">Listar</a></li>
                </ul>
            </div>
            <div class="dropdown dropdown-hover">
                <div tabindex="0" role="button" class="btn btn-secondary m-1">
                Odontologos
                <span class="rotate-90">
                        &#10097;
                    </span>
                </div>
                <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-neutral rounded-box w-52 text-neutral-content">
                    <li><a href="post_odontologos.html">Agregar</a></li>
                    <li><a href="get_odontologos.html">Listar</a></li>
                </ul>
            </div>
            <div class="dropdown dropdown-hover">
                <div tabindex="0" role="button" class="btn btn-secondary m-1">
                Turnos
                <span class="rotate-90">
                        &#10097;
                    </span>
                </div>
                <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-neutral rounded-box w-52 text-neutral-content">
                    <li><a href="post_turnos.html">Agregar</a></li>
                    <li><a href="get_turnos.html">Listar</a></li>
                </ul>
            </div>
        </div>

        <div class="navbar-end">
                        <label class=" btn btn-ghost swap swap-rotate ">
                <!-- this hidden checkbox controls the state -->
                <input type="checkbox" class=" theme-controller data-toggle-theme" data-toggle-theme="night,retro"
                    data-act-class="ACTIVECLASS" value="">
                    <!-- sun icon -->
                    <svg class="swap-off fill-current w-8 h-8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path
                            d="M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z" />
                    </svg>

                    <!-- moon icon -->
                    <svg class="swap-on fill-current w-8 h-8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path
                            d="M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z" />
                    </svg>
                </input>
            </label>
            <div class="dropdown dropdown-end">
                <div tabindex="0" role="button" class="btn btn-ghost sm:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M4 6h16M4 12h8m-8 6h16" />
                    </svg>
                </div>
                <ul tabindex="0"
                    class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
                    <li>
                        <a href="get_pacientes.html">Pacientes</a>
                        <ul class="p-2">
                            <li><a href="post_pacientes.html">Agregar</a></li>
                            <li><a href="get_pacientes.html">Listar</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="get_odontologos.html">Odontologos</a>
                        <ul class="p-2">
                            <li><a href="post_odontologos.html">Agregar</a></li>
                            <li><a href="get_odontologos.html">Listar</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="get_turnos.html">Turnos</a>
                        <ul class="p-2">
                            <li><a href="post_turnos.html">Agregar</a></li>
                            <li><a href="get_turnos.html">Listar</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    <a class="btn btn-error mx-2" href="/logout">Logout</a>
    </div>

  `;
}

document.addEventListener("DOMContentLoaded", function () {
  const navbarHTML = crearNavbar();
  const navbarContainer = document.getElementById("navbar-container");
  navbarContainer.innerHTML = navbarHTML;
});

document.addEventListener("load", function () {
  // Obtiene todas las cookies del documento
  const cookies = document.cookie.split(";");

  // Itera sobre todas las cookies y las establece con una fecha de expiración pasada (para eliminarlas)
  cookies.forEach(function (cookie) {
    const cookieParts = cookie.split("=");
    const cookieName = cookieParts[0].trim();
    document.cookie =
      cookieName + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  });

  console.log("Cookies reseteadas correctamente.");
});

function themeToggle(){var toggleEl=document.querySelector("[data-toggle-theme]");(function(theme=localStorage.getItem("theme")){if(localStorage.getItem("theme")){document.documentElement.setAttribute("data-theme",theme);if(toggleEl){[...document.querySelectorAll("[data-toggle-theme]")].forEach(el=>{el.classList.add(toggleEl.getAttribute("data-act-class"))})}}})();if(toggleEl){[...document.querySelectorAll("[data-toggle-theme]")].forEach(el=>{el.addEventListener("click",function(){var themesList=el.getAttribute("data-toggle-theme");if(themesList){var themesArray=themesList.split(",");if(document.documentElement.getAttribute("data-theme")==themesArray[0]){if(themesArray.length==1){document.documentElement.removeAttribute("data-theme");localStorage.removeItem("theme")}else{document.documentElement.setAttribute("data-theme",themesArray[1]);localStorage.setItem("theme",themesArray[1])}}else{document.documentElement.setAttribute("data-theme",themesArray[0]);localStorage.setItem("theme",themesArray[0])}}[...document.querySelectorAll("[data-toggle-theme]")].forEach(el=>{el.classList.toggle(this.getAttribute("data-act-class"))})})})}}function themeBtn(){(function(theme=localStorage.getItem("theme")){if(theme!=undefined&&theme!=""){if(localStorage.getItem("theme")&&localStorage.getItem("theme")!=""){document.documentElement.setAttribute("data-theme",theme);var btnEl=document.querySelector("[data-set-theme='"+theme.toString()+"']");if(btnEl){[...document.querySelectorAll("[data-set-theme]")].forEach(el=>{el.classList.remove(el.getAttribute("data-act-class"))});if(btnEl.getAttribute("data-act-class")){btnEl.classList.add(btnEl.getAttribute("data-act-class"))}}}else{var btnEl=document.querySelector("[data-set-theme='']");if(btnEl.getAttribute("data-act-class")){btnEl.classList.add(btnEl.getAttribute("data-act-class"))}}}})();[...document.querySelectorAll("[data-set-theme]")].forEach(el=>{el.addEventListener("click",function(){document.documentElement.setAttribute("data-theme",this.getAttribute("data-set-theme"));localStorage.setItem("theme",document.documentElement.getAttribute("data-theme"));[...document.querySelectorAll("[data-set-theme]")].forEach(el=>{el.classList.remove(el.getAttribute("data-act-class"))});if(el.getAttribute("data-act-class")){el.classList.add(el.getAttribute("data-act-class"))}})})}function themeSelect(){(function(theme=localStorage.getItem("theme")){if(localStorage.getItem("theme")){document.documentElement.setAttribute("data-theme",theme);var optionToggler=document.querySelector("select[data-choose-theme] [value='"+theme.toString()+"']");if(optionToggler){[...document.querySelectorAll("select[data-choose-theme] [value='"+theme.toString()+"']")].forEach(el=>{el.selected=true})}}})();if(document.querySelector("select[data-choose-theme]")){[...document.querySelectorAll("select[data-choose-theme]")].forEach(el=>{el.addEventListener("change",function(){document.documentElement.setAttribute("data-theme",this.value);localStorage.setItem("theme",document.documentElement.getAttribute("data-theme"));[...document.querySelectorAll("select[data-choose-theme] [value='"+localStorage.getItem("theme")+"']")].forEach(el=>{el.selected=true})})})}}function themeChange(attach=true){if(attach===true){document.addEventListener("DOMContentLoaded",function(event){themeToggle();themeSelect();themeBtn()})}else{themeToggle();themeSelect();themeBtn()}}if(typeof exports!="undefined"){module.exports={themeChange:themeChange}}else{themeChange()}