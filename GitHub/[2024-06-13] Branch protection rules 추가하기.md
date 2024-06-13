## 이슈
main 브랜치에 Branch protection rule을 만들었는데도 적용이 되지 않는 이슈가 있었습니다.

## 원인
1. 추가한 규칙이 Disabled 되어 있었음 -> Active로 바꾸어 해결했음
2. target 브랜치 리스트가 비어 있었음 -> main 브랜치를 추가하여 해결했음

<br><br>

# 📚 Branch protection rule 추가하기
### 1. 리포지토리 Settings > Branches > Add branch ruleset 선택
  ![image](https://github.com/1004s/Study/assets/124178635/ce8410fb-dc29-4761-8315-e810d920807e)

<br>

### 2. 추가한 규칙은 Settings > Rules > Rulesets 에서 확인 가능합니다.
  ![image](https://github.com/1004s/Study/assets/124178635/31283a72-499f-4644-8a99-0db81803f664)

<br>

### 3. 규칙 이름을 설정하고, Enforcement status 를 Active로 바꿔줍니다.
  ![image](https://github.com/1004s/Study/assets/124178635/ddd4aada-1362-4b4f-a02b-9fa6186d72b9)

<br>

### 4. Target Branches에 특정 브랜치 또는 브랜치 이름 패턴을 등록합니다.
   ![image](https://github.com/1004s/Study/assets/124178635/fddec43e-1b19-4a5a-bf9d-2af6a0222611)
   현재 rule은 `main` 브랜치를 업데이트하기 위한 규칙이라는 뜻

<br>

### 5. 원하는대로 Branch protection rules 추가합니다.
   ![image](https://github.com/1004s/Study/assets/124178635/54e1aca5-fe1b-4819-825b-31e0c9753d78)

<br>

## 🐥 알고리즘 리포지토리의 경우, 다음 규칙들을 적용해 두었습니다!
![image](https://github.com/1004s/Study/assets/124178635/8e0d581f-f4a7-4260-bafb-ce41eed4df51)
- 타겟 브랜치로 머지하기 전 반드시 PR을 던져야 함
- 최소 1명에게 Approval을 받아야 함
- force push 금지
