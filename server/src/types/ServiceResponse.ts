interface ServiceResponsePayload {
	message: string,
	data?: unknown
}

export default interface ServiceResponse {
	success: boolean,
	statusCode: number,
	payload: ServiceResponsePayload
}