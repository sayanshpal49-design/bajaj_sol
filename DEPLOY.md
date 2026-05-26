# Deploy Spring Boot API — GitHub + Render

Folder: `C:\Users\hp\bfhl-api`

---

## 1. GitHub

### Create repository

1. Open https://github.com/new
2. Name: `bfhl-api`
3. **Public** repository
4. Do **not** add README / .gitignore (project already has them)
5. Click **Create repository**

### Push code

Replace `YOUR_GITHUB_USERNAME`, then run in PowerShell:

```powershell
cd C:\Users\hp\bfhl-api
git init
git add .
git commit -m "BFHL Spring Boot API"
git branch -M main
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/bfhl-api.git
git push -u origin main
```

**Login:** If asked for a password, use a GitHub **Personal Access Token** (not your account password):

https://github.com/settings/tokens → *Generate new token (classic)* → enable **repo** → copy token → paste when prompted.

---

## 2. Render (Docker)

1. Sign up at https://render.com (use **Sign in with GitHub**)
2. **New +** → **Web Service**
3. Connect repository `bfhl-api`
4. Settings:

| Setting | Value |
|---------|--------|
| Name | `bfhl-api-sayansh` |
| Region | Singapore or closest |
| Branch | `main` |
| **Language** | **Docker** |
| Dockerfile path | `./Dockerfile` |
| Plan | Free |

5. **Environment variables** (optional — already in `application.properties`, but good to set on Render):

| Key | Value |
|-----|--------|
| `BFHL_USER_FULL_NAME` | `sayansh_pal` |
| `BFHL_USER_DOB_DDMMYYYY` | `30102005` |
| `BFHL_USER_EMAIL` | `sayanshpal@gmail.com` |
| `BFHL_USER_ROLL_NUMBER` | `0827CI231121` |

6. **Create Web Service** — first deploy takes ~5–10 minutes (Maven build in Docker).

7. When status is **Live**, open the URL shown (e.g. `https://bfhl-api-sayansh.onrender.com`).

---

## 3. Test live API

```powershell
curl.exe -X POST https://bfhl-api-sayansh.onrender.com/bfhl -H "Content-Type: application/json" -d "{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}"
```

Expected in response:

- `"user_id":"sayansh_pal_30102005"`
- `"sum":"339"`
- `"concat_string":"Ra"`

**Note:** Free tier sleeps after ~15 minutes idle; first request may take 30–60 seconds.

---

## 4. Submit on Microsoft form

Use your Render URL + `/bfhl`:

```
https://bfhl-api-sayansh.onrender.com/bfhl
```

(Replace host with your actual Render service name.)

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| Build failed on Render | Check **Logs**; ensure `pom.xml` and `src/` are in repo root |
| 404 on `/bfhl` | Must use **POST**, not GET in browser |
| Push rejected | Run `git pull origin main --rebase` then push again |
| Maven not found locally | Install Maven or use Docker: `docker build -t bfhl .` then `docker run -p 8080:8080 bfhl` |
