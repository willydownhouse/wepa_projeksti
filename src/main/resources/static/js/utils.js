export const fetchComments = (url, el) => {
  fetch(url)
    .then((res) => res.json())
    .then((data) => {
      console.log(data);

      if (data.length === 0) {
        el.innerHTML =
          "<p class='text-muted'><small>This tweet does not have any comments yet.</small></p>";
      } else {
        el.innerHTML = buildCommentElement(data);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

export const addComment = (url, obj, el) => {
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(obj),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      //fetch after adding
      fetchComments(url, el);
    })
    .catch((err) => {
      console.log(err);
    });
};

const buildCommentElement = (data) => {
  const html = data
    .map((comment) => {
      return `<li class="list-group-item border-0">
              <div class="card border-0">
                <div class="card-body">
                  <h6 class="card-subtitle mb-2 text-muted">
                    @${comment.account.username}
                  </h6>
                  <p class="card-text">${comment.text}</p>
                  <h6
                    id="date"
                    class="card-subtitle mb-2 text-muted"
                  ><small>
                  ${comment.createdAt}</small>
                    
                  </h6>
                </div>
              </div>
            </li>`;
    })
    .join(" ");

  return html;
};

//.split("T")[1].substring(0, 5)

const buildUserList = (data) => {
  const html = data
    .map((user) => {
      return `<a href="/users/${user.username}" class="list-group-item list-group-item-action">
      <button class="btn btn-link">@${user.username}</button>
      
      </a>`;
    })
    .join(" ");

  return html;
};

export const fetchUsers = (url, el) => {
  fetch(url, {})
    .then((res) => res.json())
    .then((data) => {
      console.log(data);

      if (data.length == 0) {
        el.innerHTML = `<div class='alert alert-danger' role='alert'>
        No users found. Please try again!
      </div>`;
      } else {
        el.innerHTML = buildUserList(data);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

export const deleteTweet = (url, id) => {
  fetch(url, {
    method: "DELETE",
  })
    .then((res) => {
      console.log(res);

      if (res.ok) {
        location.reload();
      }
    })
    .catch((err) => {
      console.log("There was error deleting this tweet");
    });
};
