import axios from "axios"
import { API_SERVER_HOST } from "./todoApi"

const host = `${API_SERVER_HOST}/api/products`

export const postAdd = async (product) => {

  const header = {headers: {"Content-Type": "multipart/form-data"}}

  // 경로 뒤 '/' 주의 
  const res = await axios.post(`${host}/`, product, header)

  return res.data

}

// 목록 페이지와 목폭 컴포넌트 처리
export const getList = async ( pageParam ) => {

  const {} = pageParam

  const res = await axios.get(`${host}/list`)
  
  return res.data

}


//특정 상품 데이터를 조회하는 작업
export const getOne = async (tno) => {

  const res = await axios.get(`${host}/${tno}` )

  return res.data

}

// 수정
export const putOne = async (pno, product) => {

  const header = {headers: {"Content-Type": "multipart/form-data"}}

  const res = await axios.put(`${host}/${pno}`, product, header)

  return res.data

}

//삭제
export const deleteOne = async (pno) => {

  const res = await axios.delete(`${host}/${pno}`)

  return res.data

}
