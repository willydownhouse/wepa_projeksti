import { fetchUsers } from "./utils.js";

const searchUsersForm = document.getElementById("searchUsersForm");
const searchUsersInput = document.getElementById("searchUsersInput");
const modal = document.querySelector(".myModal");
const overlay = document.querySelector(".overlay");
const userList = document.getElementById("userList");
const addPhotoModalBtn = document.getElementById("addPhotoModal");
const addPhotoModal = document.querySelector(".addPictureModal");

//ADD PHOTO MODAL

if (addPhotoModalBtn) {
  addPhotoModalBtn.addEventListener("click", () => {
    overlay.classList.remove("hidden");
    addPhotoModal.classList.remove("hidden");
  });
}

//SEARCH USERS FORM

if (searchUsersForm) {
  searchUsersForm.addEventListener("submit", (e) => {
    e.preventDefault();
    overlay.classList.remove("hidden");
    modal.classList.remove("hidden");

    var url = contextRoot + `users?username=${searchUsersInput.value}`;
    fetchUsers(url, userList);
  });
}

overlay.addEventListener("click", () => {
  overlay.classList.add("hidden");
  modal.classList.add("hidden");
  addPhotoModal.classList.add("hidden");
});
