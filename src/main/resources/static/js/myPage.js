import { fetchComments, addComment, fetchUsers } from "./utils.js";

const allCommentsButtons = document.querySelectorAll("#commentsBtn");
const allCommentFormWrappers = document.querySelectorAll(".commentFormWrapper");
const addCommenttForms = document.querySelectorAll(".addCommentForm");
const searchUsersForm = document.getElementById("searchUsersForm");
const searchUsersInput = document.getElementById("searchUsersInput");
const modal = document.querySelector(".myModal");
const overlay = document.querySelector(".overlay");
const userList = document.getElementById("userList");
const addPhotoModalBtn = document.getElementById("addPhotoModal");
const addPhotoModal = document.querySelector(".addPictureModal");

let clickedTweet;
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

// SHOW COMMENTS

allCommentsButtons.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    const commentWrapperEl = e.target
      .closest(".card-body")
      .querySelector(".commentFormWrapper");

    const commentListEl =
      e.target.nextElementSibling.querySelector("#commentList");

    const tweetId = e.target.getAttribute("value");

    //hide all comments
    allCommentFormWrappers.forEach((el) => el.classList.add("displayNone"));

    //close comments
    if (clickedTweet === tweetId) {
      clickedTweet = null;
      return;
    }

    //open clicked comments
    commentWrapperEl.classList.remove("displayNone");
    clickedTweet = tweetId;

    //FETCH ALL COMMENTS
    var url = contextRoot + `tweets/${clickedTweet}/comments`;

    fetchComments(url, commentListEl);
  });
});

//ADD NEW COMMENT

addCommenttForms.forEach((form) => {
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    let comment = e.target.querySelector(".form-control").value;

    var url = contextRoot + `tweets/${clickedTweet}/comments`;

    const commentObject = {
      text: comment,
      createdAt: new Date().toISOString(),
    };

    const commentsListElement = e.target.previousElementSibling;

    addComment(url, commentObject, commentsListElement);

    e.target.querySelector(".form-control").value = "";
  });
});
